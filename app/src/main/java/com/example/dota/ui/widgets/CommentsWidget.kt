package com.example.dota.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.R
import com.example.dota.extentions.parse
import com.example.dota.repository.DotaRepository
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

data class Comment(
    val author: String,
    @DrawableRes val res: Int,
    val date: String,
    val text: String,
)

sealed class CommentsBlockState : WidgetState()

object CommentsBlockLoadingState : CommentsBlockState()

data class CommentsBlockLoadedState(
    val title: String,
    val comments: List<Comment>
) : CommentsBlockState()

@Composable
fun CommentsWidget(state: CommentsBlockState) {
    when(state) {
        CommentsBlockLoadingState -> CommentsWidgetPlaceholder()
        is CommentsBlockLoadedState -> CommentsWidgetLoaded(state)
    }
}

@Composable
fun CommentsWidgetPlaceholder() {
    //TODO: Добавить Placeholder
    Column(
        modifier = Modifier.padding(horizontal = 22.dp)
    ) {
        (1..3).forEach { index ->
            CommentsBlockItemPlaceholder()
            if (index != 3) {
                Spacer(Modifier.height(24.dp))
                Divider(color = Color.parse("#1A1F29"), thickness = 1.dp)
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CommentsWidgetLoaded(state: CommentsBlockLoadedState) {
    Column(
        modifier = Modifier.padding(horizontal = 22.dp)
    ) {
        state.comments.forEachIndexed { index, comment ->
            CommentsBlockItem(comment)
            if (index != state.comments.size - 1) {
                Spacer(Modifier.height(24.dp))
                Divider(color = Color.parse("#1A1F29"), thickness = 1.dp)
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CommentsBlockItem(block: Comment) {
    //TODO: Вынести цвета
    Column {
        Row {
            Image(painterResource(block.res), contentDescription = "Avatar Photo")
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = block.author,
                    color = Color.White,
                )
                Text(
                    text = block.date,
                    color = Color.White.copy(alpha = 0.4f),
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = block.text,
            color = Color.parse("#A8ADB7")
        )
    }
}

@Composable
@Preview
fun CommentsBlockItemPlaceholder() {
    Column {
        Row {
            Text(
                text = "Test",
                modifier = Modifier
                    .size(20.dp)
                    .placeholder(
                        visible = true,
                        shape = CircleShape,
                        highlight = PlaceholderHighlight.fade(Color.Gray)
                    )
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Test",
                    color = Color.White,
                    modifier = Modifier
                        .width(40.dp)
                        .height(5.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade(Color.Gray)
                        )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Test",
                    color = Color.White.copy(alpha = 0.4f),
                    modifier = Modifier
                        .width(40.dp)
                        .height(5.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade(Color.Gray)
                        )
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Test",
            color = Color.parse("#A8ADB7"),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .placeholder(
                    visible = true,
                    shape = CircleShape,
                    highlight = PlaceholderHighlight.fade(Color.Gray)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentsBlockPreview() {
    Box(
        Modifier
            .wrapContentSize()
            .background(Color.parse("#050B18"))
            .padding(22.dp)
    ) {
        CommentsWidget(DotaRepository().mockCommentsState())
    }
}