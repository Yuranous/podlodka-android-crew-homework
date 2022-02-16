package com.example.dota.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.R
import com.example.dota.repository.DotaRepository
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

data class Screenshot(
    @DrawableRes val res: Int,
)

sealed class ScreenshotWidgetState : WidgetState()

object ScreenshotWidgetLoadingState : ScreenshotWidgetState()

data class ScreenshotWidgetLoadedState(
    val screenshots: List<Screenshot>,
) : ScreenshotWidgetState()

val screenshotModifier = Modifier
    .width(240.dp)
    .height(120.dp)
    .clip(RoundedCornerShape(14.dp))

@Composable
fun ScreenshotWidget(state: ScreenshotWidgetState) {
    when (state) {
        ScreenshotWidgetLoadingState -> ScreenshotWidgetPlaceholder()
        is ScreenshotWidgetLoadedState -> ScreenshotWidgetLoaded(state)
    }
}

@Composable
fun ScreenshotWidgetPlaceholder() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(Modifier.width(6.dp))
        }
        items(3) {
            Text(
                text = "",
                modifier = screenshotModifier
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(Color.Gray)
                    )
            )
        }
    }
}

@Composable
fun ScreenshotWidgetLoaded(state: ScreenshotWidgetLoadedState) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(Modifier.width(6.dp))
        }

        items(state.screenshots) { screenshot ->
            ScreenshotWidgetItem(screenshot)
        }
    }
}

@Composable
fun ScreenshotWidgetItem(state: Screenshot) {
    Box {
        Image(
            modifier = screenshotModifier,
            painter = painterResource(id = state.res),
            contentDescription = "Game Screenshot",
            contentScale = ContentScale.Crop,
        )
        PlayButton(Modifier.align(Alignment.Center))
    }
}

@Composable
fun PlayButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.24f))
            .size(48.dp)
            .clickable { }
    )
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow),
        contentDescription = "Play Button",
        tint = Color.White,
        modifier = modifier
    )
}

@Preview
@Composable
fun ScreenshotWidgetPlaceholderPreview() {
    ScreenshotWidgetPlaceholder()
}

@Preview
@Composable
fun ScreenshotWidgetLoadedPreview() {
    val repository = DotaRepository()
    ScreenshotWidgetLoaded(repository.mockScreenshotState())
}
