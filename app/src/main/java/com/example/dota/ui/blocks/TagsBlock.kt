package com.example.dota.ui.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.extentions.parse
import com.example.dota.repository.DotaRepository

data class TagBlockState(
    val tags: List<String>,
)

@Composable
fun TagsBlock(blockVo: TagBlockState) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(blockVo.tags) { tag ->
            TagItem(tag)
        }
    }
}

@Composable
fun TagItem(tag: String) {
    tag.run {
        Text(
            modifier = Modifier
                .background(
                    color = Color.parse("#44A9F4", 0.24f),
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(10.dp, 5.dp),
            text = tag,
            color = Color.parse("#44A9F4"),
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun TagItemPreview() {
    Box(
        Modifier
            .background(Color.parse("#050B18"))
            .padding(30.dp)
    ) {
        TagsBlock(DotaRepository().mockTagsState())
    }
}