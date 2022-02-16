package com.example.dota.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dota.extentions.parse
import com.example.dota.repository.DotaRepository
import com.example.dota.ui.custom.StarRatingBar
import com.example.dota.ui.theme.DotaTheme

enum class Star {
    EMPTY,
    SEMI,
    FILLED,
}

data class RatingBarState(
    val stars: List<Star>,
)

sealed class RatingBlockState : WidgetState()

object RatingBlockLoadingState : RatingBlockState()

data class RatingBlockLoadedState(
    val title: String,
    val ratingValue: String,
    val ratingBar: RatingBarState,
    val reviewText: String
) : RatingBlockState()

@Composable
fun RatingWidget(state: RatingBlockState) {
    when (state) {
        RatingBlockLoadingState -> RatingWidgetPlaceholder()
        is RatingBlockLoadedState -> RatingWidgetLoaded(state)
    }
}

@Composable
fun RatingWidgetPlaceholder() {

}

@Composable
fun RatingWidgetLoaded(state: RatingBlockLoadedState) {
    ConstraintLayout(
        modifier = Modifier.padding(horizontal = 22.dp)
    ) {
        val (ratingTitle, ratingValue, ratingBar, reviews) = createRefs()

        Text(
            modifier = Modifier.constrainAs(ratingTitle) {},
            text = state.title,
            style = MaterialTheme.typography.h3,
        )

        Text(
            modifier = Modifier.constrainAs(ratingValue) {
                top.linkTo(ratingTitle.bottom, 12.dp)
            },
            text = state.ratingValue,
            style = MaterialTheme.typography.h1,
        )

        StarRatingBar(
            modifier = Modifier.constrainAs(ratingBar) {
                top.linkTo(ratingTitle.bottom, 29.dp)
                start.linkTo(ratingValue.end, 16.dp)
            },
            blockState = state.ratingBar
        )

        Text(
            modifier = Modifier.constrainAs(reviews) {
                top.linkTo(ratingTitle.bottom, 8.dp)
                start.linkTo(ratingValue.end, 16.dp)
                baseline.linkTo(ratingValue.baseline)
            },
            text = state.reviewText,
            style = MaterialTheme.typography.body1,
        )
    }
}


@Preview(showBackground = false)
@Composable
private fun TagItemPreview() {
    Box(
        Modifier
            .wrapContentSize()
            .background(Color.parse("#050B18"))
            .padding(30.dp)
    ) {
        val repository = DotaRepository()
        DotaTheme {
            RatingWidget(repository.mockRatingState())
        }
    }
}