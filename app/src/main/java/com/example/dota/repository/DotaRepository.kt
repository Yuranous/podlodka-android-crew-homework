package com.example.dota.repository

import com.example.dota.R
import com.example.dota.ui.blocks.HeaderBlockState
import com.example.dota.ui.blocks.TagBlockState
import com.example.dota.ui.widgets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DotaRepository {

    fun mockHeaderState() = HeaderBlockState(
        title = "DoTa 2",
        icon = R.drawable.dota,
        ratingBar = RatingBarState(
            listOf(
                Star.FILLED,
                Star.FILLED,
                Star.FILLED,
                Star.FILLED,
                Star.FILLED,
            )
        ),
        comments = "70M",
    )

    fun mockTagsState() = TagBlockState(
        tags = listOf(
            "MOBA",
            "MULTIPLAYER",
            "STRATEGY",
        ),
    )

    fun mockDescriptionState() = DescriptionBlockLoadedState(
        headerBlockState = mockHeaderState(),
        tagBlockState = mockTagsState(),
        description = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.",
    )

    fun mockCommentsState() = CommentsBlockLoadedState(
        title = "",
        comments = listOf(
            Comment(
                author = "Auguste Conte",
                res = R.drawable.avatar1,
                date = "February 14, 2019",
                text = "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
            ),
            Comment(
                author = "Jang Marcelino",
                res = R.drawable.avatar2,
                date = "February 14, 2019",
                text = "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
            ),
        )
    )

    fun mockRatingState() = RatingBlockLoadedState(
        title = "Review & Ratings",
        ratingValue = "4.9",
        ratingBar = RatingBarState(
            stars = listOf(
                Star.FILLED,
                Star.FILLED,
                Star.FILLED,
                Star.FILLED,
                Star.SEMI,
            )
        ),
        reviewText = "70M Reviews",
    )

    fun mockScreenshotState() = ScreenshotWidgetLoadedState(
        screenshots = listOf(
            Screenshot(R.drawable.screenshot1),
            Screenshot(R.drawable.screenshot2),
        )
    )

    suspend fun getDescription(): DescriptionBlockLoadedState = withContext(Dispatchers.IO) {
        mockDescriptionState()
    }

    suspend fun getComments(): CommentsBlockLoadedState = withContext(Dispatchers.IO) {
        delay(10000)
        mockCommentsState()
    }

    suspend fun getScreenshots(): ScreenshotWidgetLoadedState = withContext(Dispatchers.IO) {
        delay(10000)
        mockScreenshotState()
    }

    suspend fun getRating(): RatingBlockLoadedState = withContext(Dispatchers.IO) {
        mockRatingState()
    }

    suspend fun getWidgets(): List<Widget> = withContext(Dispatchers.IO) {
        delay(2000)
        listOf(
            Widget.DESCRIPTION,
            Widget.SCREENSHOTS,
            Widget.RATING,
            Widget.COMMENTS,
        )
    }
}