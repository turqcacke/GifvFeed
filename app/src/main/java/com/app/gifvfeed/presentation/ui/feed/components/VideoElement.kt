package com.app.gifvfeed.presentation.ui.feed.components

import android.media.MediaPlayer
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.gifvfeed.data.network.MediaUrl
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun VideoElement(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    thumbLink: String,
    exoPlayer: ExoPlayer,
) {

    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isPlaying) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize(),
                factory = {
                    StyledPlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        exoPlayer.seekTo(C.TIME_UNSET)
                        exoPlayer.playWhenReady = true
                    }
                }
            )
        } else {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = MediaUrl.getVideoUrl(thumbLink),
                success = { imageState ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val painter = rememberDrawablePainter(drawable = imageState.drawable)
                        Image(
                            modifier = modifier,
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            )
        }
    }
}