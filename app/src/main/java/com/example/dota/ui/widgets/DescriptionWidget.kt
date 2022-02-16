package com.example.dota.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.repository.DotaRepository
import com.example.dota.ui.blocks.HeaderBlock
import com.example.dota.ui.blocks.HeaderBlockState
import com.example.dota.ui.blocks.TagBlockState
import com.example.dota.ui.blocks.TagsBlock
import com.google.accompanist.placeholder.material.placeholder

sealed class DescriptionBlockState : WidgetState()

object DescriptionBlockLoadingState : DescriptionBlockState()

data class DescriptionBlockLoadedState(
    val headerBlockState: HeaderBlockState,
    val tagBlockState: TagBlockState,
    val description: String,
) : DescriptionBlockState()

@Composable
fun DescriptionWidget(state: DescriptionBlockState) {

    when (state) {
        DescriptionBlockLoadingState -> DescriptionWidgetPlaceholder()
        is DescriptionBlockLoadedState -> DescriptionWidgetLoaded(state)
    }
}

@Composable
fun DescriptionWidgetPlaceholder() {
    //TODO: Добавить placeholder
    Column {
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
        ) {
            Text(
                text = "Test",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 22.dp)
                    .placeholder(visible = true, color = Color.Gray)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Test",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 22.dp)
                    .placeholder(visible = true, color = Color.Gray)
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Test",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 22.dp)
                    .placeholder(visible = true, color = Color.Gray)
            )
        }
    }
}

@Composable
fun DescriptionWidgetLoaded(state: DescriptionBlockLoadedState) {
    Column {
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp)
        ) {
            HeaderBlock(state.headerBlockState)
            Spacer(Modifier.height(16.dp))
            TagsBlock(state.tagBlockState)
            Spacer(Modifier.height(24.dp))
            Text(
                text = state.description,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Composable
@Preview
fun DescriptionWidgetPlaceholderPreview() {
    DescriptionWidgetPlaceholder()
}

@Composable
@Preview
fun DescriptionWidgetLoadedPreview() {
    val repository = DotaRepository()
    DescriptionWidgetLoaded(repository.mockDescriptionState())
}