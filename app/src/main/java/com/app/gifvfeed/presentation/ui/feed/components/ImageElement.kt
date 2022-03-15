package com.app.gifvfeed.presentation.ui.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.app.gifvfeed.R
import com.app.gifvfeed.data.network.MediaUrl
import com.app.gifvfeed.domain.entity.EntryBlock
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.rememberDrawablePainter

@Composable
fun ImageElement(
    modifier: Modifier = Modifier,
    entryBlock: EntryBlock.MediaBlock,
) {
    GlideImage(
        modifier = modifier.fillMaxSize(),
        imageModel = MediaUrl.getImageCorpUrl(entryBlock.data.uuid),
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
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

            }
        },
        failure = {
            Text(stringResource(id = R.string.image_upload_fail))
        },
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = Color.White,
            durationMillis = 350,
            dropOff = 0.65f,
        )
    )

}



