package com.example.dota.ui.blocks

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dota.R
import com.example.dota.extentions.parse
import com.example.dota.repository.DotaRepository
import com.example.dota.ui.custom.StarRatingBar
import com.example.dota.ui.theme.DotaTheme
import com.example.dota.ui.widgets.RatingBarState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder

data class HeaderBlockState(
    val title: String,
    val ratingBar: RatingBarState,
    val comments: String,
    @DrawableRes val icon: Int,
)

@Composable
fun HeaderBlock(blockState: HeaderBlockState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GameIcon()
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = blockState.title,
                style = MaterialTheme.typography.h2,
            )
            Spacer(Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StarRatingBar(blockState.ratingBar)
                Spacer(Modifier.width(10.dp))
                Text(
                    text = blockState.comments,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

@Composable
fun GameIcon() {
    Box(
        modifier = Modifier
            .border(width = 2.dp, color = Color.parse("#1F2430"), shape = RoundedCornerShape(13.dp))
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(13.dp)
            ),
    ) {
        Image(
            modifier = Modifier
                .padding(17.dp)
                .width(54.dp)
                .height(54.dp),
            painter = painterResource(id = R.drawable.dota),
            contentDescription = "Game Icon",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderBlockPreview() {
    DotaTheme {
        Box(
            Modifier
                .wrapContentSize()
                .background(Color.parse("#050B18"))
                .padding(22.dp)
        ) {
            HeaderBlock(DotaRepository().mockHeaderState())
        }
    }
}