package com.app.gifvfeed.presentation.ui.feed.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.gifvfeed.R
import com.app.gifvfeed.presentation.ui.theme.DefaultOrange

@Composable
fun LinkElement(
    modifier: Modifier,
    link: String,
    text: String,
    click: () -> Unit = {}
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(50),
            backgroundColor = DefaultOrange
        ) {
            val painter = painterResource(id = R.drawable.ic_link)
            Image(
                modifier = Modifier.padding(5.dp),
                painter = painter,
                contentDescription = "Link icon"
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
            )
            Text(
                text = link,
                color = Color.Gray
            )
        }


        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black,
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier,
            border = BorderStroke(
                2.dp, DefaultOrange
            ),
            shape = RoundedCornerShape(25),
            onClick = { click() }) {
            Text(text = "Go")
        }
    }
}

@Preview
@Composable
fun LinkElementPreview() {
    val link = "https://tjournal.ru/u/458157-turquoise-cacke"
    LinkElement(
        modifier = Modifier.fillMaxWidth(),
        link = link,
        text = "dsa"
    )
}