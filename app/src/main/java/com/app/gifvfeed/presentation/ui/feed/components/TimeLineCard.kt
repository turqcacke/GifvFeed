package com.app.gifvfeed.presentation.ui.feed.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.gifvfeed.R
import com.app.gifvfeed.data.network.MediaUrl
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.presentation.ui.theme.LightGreen
import com.app.gifvfeed.presentation.ui.theme.LightRed
import com.app.gifvfeed.presentation.ui.utils.Utils.parseColorFromString
import com.app.gifvfeed.presentation.utils.NumberFormatter
import com.google.android.exoplayer2.ExoPlayer
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun TimeLineCard(
    modifier: Modifier = Modifier,
    timeLineItem: TimeLineItem,
    exoPlayer: ExoPlayer,
    isPlaying: Boolean = false,
) {
    val uriHandler = LocalUriHandler.current

    val timeLineItemEntry = when (timeLineItem) {
        is TimeLineItem.TimeLineEntry -> timeLineItem
    }
    val textBlocks = timeLineItemEntry.data.blocks.filterIsInstance<EntryBlock.TextBlock>()
    val linkBlocks = timeLineItem.data.blocks.filter {
        it is EntryBlock.YouTubeVideoBlock || it is EntryBlock.InstagramBlock
    }
    val medialBlocks = timeLineItemEntry.data.blocks.filterIsInstance<EntryBlock.MediaBlock>()

    ConstraintLayout(
        modifier = modifier.padding(0.dp, dimensionResource(id = R.dimen.dimen_default_16))
    ) {
        val (title, media, links, stats) = createRefs()
        ConstraintLayout(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_default_16), 0.dp)
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(title.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (subsiteInfo, header, description) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        0.dp,
                        0.dp,
                        0.dp,
                        10.dp
                    )
                    .constrainAs(subsiteInfo) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                GlideImage(
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp),
                    imageModel = MediaUrl.getImageCorpUrl(
                        timeLineItemEntry.data.subsite.avatar.uuid,
                        MediaUrl.CorpRes.R22x22
                    ),
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.White,
                        durationMillis = 350,
                        dropOff = 0.65f,
                    ),
                    success = { imageState ->
                        imageState.drawable?.let {
                            val painter = rememberDrawablePainter(drawable = it)
                            RoundedIcon(
                                modifier = Modifier.fillMaxSize(),
                                painter = painter,
                                contentDesc = "Loaded icon",
                                borderColor = parseColorFromString(timeLineItemEntry.data.subsite.avatar.color)
                            )
                        }
                    },
                    failure = {
                        val painter = painterResource(id = R.drawable.default_icon)
                        RoundedIcon(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDesc = "Default Icon",
                            borderColor = MaterialTheme.colors.primary
                        )
                    }
                )
                Text(
                    text = timeLineItemEntry.data.subsite.name,
                    modifier = Modifier.padding(
                        8.dp,
                        0.dp,
                        0.dp,
                        0.dp,
                    ),
                    fontSize = dimensionResource(id = R.dimen.font_default_16).value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = timeLineItemEntry.data.author.name,
                    modifier = Modifier.padding(
                        12.dp,
                        0.dp,
                        0.dp,
                        0.dp,
                    ),
                    fontSize = dimensionResource(id = R.dimen.font_lower_15).value.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(header) {
                        start.linkTo(parent.start)
                        top.linkTo(subsiteInfo.bottom)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (timeLineItemEntry.data.title != "") {
                    Text(
                        text = timeLineItemEntry.data.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.font_medium_22).value.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(
                        description
                    ) {
                        start.linkTo(parent.start)
                        top.linkTo(header.bottom)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (textBlocks.isNotEmpty()) {
                    var text = ""
                    textBlocks.forEach {
                        text += it.text
                        if (textBlocks.indexOf(it) != textBlocks.lastIndex) {
                            text += "\n"
                        }
                    }
                    Text(
                        text = text,
                        fontSize = dimensionResource(id = R.dimen.font_default_16).value.sp
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, dimensionResource(id = R.dimen.dimen_low_12))
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(media) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (medialBlocks.isNotEmpty()) {
                val mediaBlock = medialBlocks[0]
                when (mediaBlock.data.isPlayable) {
                    true -> {
                        VideoElement(
                            modifier = Modifier
                                .background(Color.Black)
                                .height(210.dp),
                            isPlaying = isPlaying,
                            thumbLink = mediaBlock.data.uuid,
                            exoPlayer = exoPlayer
                        )
                    }
                    false -> {
                        ImageElement(
                            modifier = Modifier
                                .background(Color.Black)
                                .height(210.dp),
                            entryBlock = mediaBlock
                        )
                    }
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    dimensionResource(id = R.dimen.dimen_default_16),
                    0.dp
                )
                .constrainAs(links) {
                    top.linkTo(media.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            if (linkBlocks.isNotEmpty()) {
                linkBlocks.forEach { block ->
                    when (block) {
                        is EntryBlock.YouTubeVideoBlock -> {
                            val link = "https://www.youtube.com/watch?v=${block.video.external.id}"
                            LinkElement(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                link = link,
                                text = "YouTube",
                                click = {
                                    uriHandler.openUri(link)
                                }
                            )
                        }
                        is EntryBlock.InstagramBlock -> {
                            LinkElement(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                link = block.url,
                                text = "Instagram",
                                click = {
                                    uriHandler.openUri(block.url)
                                }
                            )
                        }
                        else -> {}
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    dimensionResource(id = R.dimen.dimen_default_16),
                    dimensionResource(id = R.dimen.dimen_low_12),
                    dimensionResource(id = R.dimen.dimen_default_16),
                    0.dp
                )
                .constrainAs(stats) {
                    top.linkTo(links.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val commentPainter = painterResource(
                    id = R.drawable.ic_comment
                )
                Icon(
                    painter = commentPainter,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    modifier = Modifier.padding(
                        4.dp, 0.dp, 0.dp, 0.dp
                    ),
                    text = NumberFormatter.format(timeLineItemEntry.data.counter.comments),
                    fontSize = dimensionResource(id = R.dimen.font_default_16).value.sp,
                )
            }
            Text(
                modifier = Modifier,
                text = NumberFormatter.format(timeLineItemEntry.data.counter.likes),
                color = when {
                    timeLineItemEntry.data.counter.likes > 0 -> LightGreen
                    timeLineItemEntry.data.counter.likes < 0 -> LightRed
                    else -> Color.Gray
                },
                fontSize = dimensionResource(id = R.dimen.font_default_16).value.sp
            )
        }

    }
}

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDesc: String,
    borderColor: Color
) {
    Card(
        shape = RoundedCornerShape(30),
        modifier = modifier,
        border = BorderStroke(
            1.dp,
            borderColor
        )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = contentDesc,
        )
    }
}