package com.example.dota.ui.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dota.R
import com.example.dota.ui.widgets.RatingBarState
import com.example.dota.ui.widgets.Star


@Composable
fun StarRatingBar(blockState: RatingBarState, modifier: Modifier = Modifier, space: Int = 4, ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space.dp)
    ) {
        blockState.stars.forEach { starVo ->
            val (painter, contentDescription) = when (starVo) {
                Star.EMPTY -> Pair(
                    painterResource(R.drawable.ic_empty_star),
                    "Empty Star"
                )
                Star.SEMI -> Pair(
                    painterResource(R.drawable.ic_semi_star),
                    "Semi-filled Star"
                )
                Star.FILLED -> Pair(painterResource(R.drawable.ic_star), "Star")
            }

            Icon(
                painter = painter,
                contentDescription = contentDescription,
                tint = Color.Unspecified,
            )
        }
    }
}