package com.app.gifvfeed.presentation.ui.feed.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.app.gifvfeed.R
import com.app.gifvfeed.data.network.MediaUrl
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlin.math.abs


@Composable
fun TimelineCardList(
    modifier: Modifier = Modifier,
    list: List<TimeLineItem>,
    isRefreshingState: State<Boolean>,
    loadMoreState: State<Boolean>? = null,
    loadMore: () -> Unit = {}
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    val loadingMore = remember {
        when (loadMoreState) {
            null -> mutableStateOf(false)
            else -> loadMoreState

        }
    }

    var playingItem by remember {
        mutableStateOf(
            list.firstOrNull()?.let {
                when (isMediaPlayable(it)) {
                    true -> return@let it
                    else -> return@let null
                }
            }
        )
    }

    LaunchedEffect(playingItem) {
        if (playingItem == null) {
            exoPlayer.pause()
        } else {
            val playingEntry =
                (playingItem as TimeLineItem.TimeLineEntry).data.mediaBlocks.firstOrNull()
            exoPlayer.setMediaItem(MediaItem.fromUri(MediaUrl.getVideoUrl(playingEntry!!.data.uuid)))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        snapshotFlow {
            listState.playingItem(list)
        }.collect { videoItem ->
            playingItem = videoItem
        }
    }

    DisposableEffect(exoPlayer) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (playingItem == null) return@LifecycleEventObserver
            when (event) {
                Lifecycle.Event.ON_START -> exoPlayer.play()
                Lifecycle.Event.ON_STOP -> exoPlayer.pause()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            exoPlayer.release()
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(list.size) {
            TimeLineCard(
                timeLineItem = list[it],
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                exoPlayer = exoPlayer,
                isPlaying = list.indexOf(playingItem) == it,
                onClickPlay = {
                    playingItem = list[it]
                }
            )
        }
        if (loadingMore.value) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(id = R.dimen.dimen_default_16),
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator()
                }
            }
        }
    }

    listState.OnBottomReached(isRefreshing = isRefreshingState) {
        loadMore()
    }
}

@Composable
private fun LazyListState.OnBottomReached(
    isRefreshing: State<Boolean>,
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastListItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true
            lastListItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) {
                    loadMore()
                }
            }
    }
}

private fun LazyListState.playingItem(
    timeLineItemList: List<TimeLineItem>
): TimeLineItem? {
    if (layoutInfo.visibleItemsInfo.isNullOrEmpty() || timeLineItemList.isEmpty()) return null
    val layoutInfo = layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val lastItem = visibleItems.last()

    val firstItemVisible = when {
        firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset == 0 -> {
            isVisibleByIndex(timeLineItemList, 0)
        }
        else -> false
    }

    val itemSize = lastItem.size
    val itemOffset = lastItem.offset
    val totalOffset = layoutInfo.viewportEndOffset

    val lastItemVisible = when {
        lastItem.index == timeLineItemList.size - 1 && totalOffset - itemOffset >= itemSize -> isVisibleByIndex(
            timeLineItemList,
            timeLineItemList.lastIndex
        )
        else -> false
    }

    val midPoint = when {
        visibleItems.size % 2 == 0 -> {
            visibleItems.size / 2
        }
        else -> (visibleItems.size / 2) + 1
    }

    val centerItems = visibleItems.sortedBy {
        abs(midPoint - (visibleItems.indexOf(it) + 1))
    }

    return when {
        firstItemVisible -> timeLineItemList.first()
        lastItemVisible -> timeLineItemList.last()
        else -> {
            for (info in centerItems) {
                val item = timeLineItemList[info.index]
                if (isMediaPlayable(item)) {
                    return timeLineItemList[info.index]
                }
            }
            return null
        }
    }
}

fun isMediaPlayable(item: TimeLineItem): Boolean {
    val firstItem = when (item) {
        is TimeLineItem.TimeLineEntry -> {
            item.data.mediaBlocks.firstOrNull()
        }
        else -> {
            null
        }
    }
    return firstItem is EntryBlock.MediaBlock && firstItem.data.isPlayable
}

fun isVisibleByIndex(timeLineItemList: List<TimeLineItem>, index: Int): Boolean {
    return when (val entryItem = timeLineItemList.getOrNull(index)) {
        is TimeLineItem.TimeLineEntry -> {
            val mediaEntry = entryItem.data.mediaBlocks.firstOrNull()
            mediaEntry is EntryBlock.MediaBlock && mediaEntry.data.isPlayable
        }
        else -> {
            false
        }
    }
}
