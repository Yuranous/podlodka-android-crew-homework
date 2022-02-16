package com.example.dota.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.R
import com.example.dota.extentions.parse
import com.example.dota.ui.viewmodel.DotaScreenLoadedState
import com.example.dota.ui.viewmodel.DotaScreenLoadingState
import com.example.dota.ui.viewmodel.DotaViewModel
import com.example.dota.ui.widgets.*
import java.lang.Float.min

@Composable
fun DotaGameScreen(
    viewModel: DotaViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when (uiState) {
            is DotaScreenLoadingState -> FullScreenLoading()
            is DotaScreenLoadedState -> DotaGameLoadedScreen(
                state = uiState as DotaScreenLoadedState,
                viewModel = viewModel
            )
        }
    }
}


@Composable
fun DotaGameLoadedScreen(
    state: DotaScreenLoadedState,
    viewModel: DotaViewModel,
) {
    val scrollState = rememberLazyListState()

    Image(
        painter = painterResource(R.drawable.dota_background),
        contentDescription = "Game Background",
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                if (scrollState.firstVisibleItemIndex == 0) {
                    alpha = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f))
                    scaleX = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 5000f))
                    scaleY = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 5000f))
                    translationY = -scrollState.firstVisibleItemScrollOffset * 0.1f
                } else {
                    alpha = 0f
                }
            }
    )
    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.offset(y = (-60).dp)
    ) {
        item {
            Box {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 130.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back arrow icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable { }
                    )
                    Icon(
                        painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable { }
                    )
                }
                Image(
                    painter = painterResource(R.drawable.dota_background),
                    contentDescription = "Game Background",
                    modifier = Modifier
                        .alpha(0f)
                        .fillMaxWidth()
                )
            }
        }

        items(state.widgets) { widget ->
            val widgetState by viewModel.getWidgetState(widget).collectAsState()
            when (widget) {
                Widget.DESCRIPTION -> DescriptionWidget(widgetState as DescriptionBlockState)
                Widget.COMMENTS -> CommentsWidget(state = widgetState as CommentsBlockState)
                Widget.SCREENSHOTS -> ScreenshotWidget(state = widgetState as ScreenshotWidgetState)
                Widget.RATING -> RatingWidget(state = widgetState as RatingBlockState)
            }
        }

        item {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.parse("#F4D144")),
                shape = RoundedCornerShape(12.dp),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .height(64.dp)
            ) {
                Text(
                    text = "Install",
                    style = MaterialTheme.typography.button,
                )
            }
            Spacer(Modifier.height(37.dp))
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}