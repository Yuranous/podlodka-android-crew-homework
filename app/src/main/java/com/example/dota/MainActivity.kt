package com.example.dota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dota.ui.screens.DotaGameScreen
import com.example.dota.ui.theme.DotaTheme
import com.example.dota.ui.viewmodel.DotaViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//val headerVo = HeaderBlockState(
//    title = "DoTa 2",
//    icon = "",
//    ratingBar = RatingBarState(
//        listOf(
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.FILLED,
//        )
//    ),
//    comments = "70M",
//)
//
//val tagsVo = TagBlockVo(
//    tags = listOf(
//        TagVo("MOBA"),
//        TagVo("MULTIPLAYER"),
//        TagVo("STRATEGY"),
//    ),
//)
//
//val ratingVo = RatingBlockState(
//    title = "Review & Ratings",
//    ratingValue = "4.9",
//    ratingBar = RatingBarState(
//        listOf(
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.FILLED,
//            StarVo.SEMI,
//        )
//    ),
//    reviewText = "70M Reviews"
//)
//
//val descriptionVo = DescriptionVo(
//    "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own."
//)
//
//val photoVo = PhotoBlockVo("")
//
//val commentsVo = CommentsBlockState(
//    title = "",
//    comments = listOf(
//        Comment(
//            author = "Auguste Conte",
//            photo = "",
//            date = "February 14, 2019",
//            text = "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
//        ),
//        Comment(
//            author = "Auguste Conte",
//            photo = "",
//            date = "February 14, 2019",
//            text = "Once you start to learn its secrets, there’s a wild and exciting variety of play here that’s unmatched, even by its peers.",
//        ),
//    )
//)

//val items = listOf(
//    photoVo,
//    headerVo,
//    tagsVo,
//    descriptionVo,
//    ratingVo,
//    commentsVo,
//)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = DotaViewModel()

        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()

            systemUiController.isStatusBarVisible = false // Status bar
            systemUiController.isNavigationBarVisible = false // Navigation bar
            systemUiController.isSystemBarsVisible = false // Status & Navigation bars
            // A surface container using the 'background' color from the theme
            DotaTheme {
                DotaGameScreen(viewModel)
            }
        }
    }
}

//@Composable
//fun Main(items: List<ItemVo>) {
//    val photoBlock = items.find { it is PhotoBlockVo }
//
//    if (photoBlock != null) {
//        GamePageWithPhoto(photoBlock as PhotoBlockVo, items)
//    } else {
//        GamePageWithoutPhoto(items)
//    }
//}
//
//@Composable
//fun GamePageWithPhoto(photoBlock: PhotoBlockVo, items: List<ItemVo>) {
//    Column() {
//        PhotoBlock(photoBlock)
//        WidgetList(
//            items,
//            Modifier
//                .padding(horizontal = 22.dp, vertical = 20.dp)
//                .offset(y = (-100).dp),
//        )
//    }
//}
//
//@Composable
//fun GamePageWithoutPhoto(items: List<ItemVo>) {
//    WidgetList(items, Modifier.padding(horizontal = 22.dp, vertical = 20.dp))
//}
//
//@Composable
//fun WidgetList(items: List<ItemVo>, modifier: Modifier = Modifier) {
//    LazyColumn(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(24.dp)
//    ) {
//        items(items) { blockVo ->
//            when (blockVo) {
//                is HeaderBlockState -> HeaderBlock(blockVo)
//                is TagBlockVo -> TagsBlock(blockVo)
//                is RatingBlockState -> RatingBlock(blockVo)
////                is DescriptionVo -> DescriptionBlock(blockVo)
//                is CommentsBlockState -> CommentsBlock(blockVo)
//                else -> {}
//            }
//        }
//
//        item {
//            Button(
//                modifier = Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 22.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.parse("#F4D144")),
//                onClick = {},
//            ) {
//                Text(
//                    modifier = Modifier.padding(horizontal = 20.dp),
//                    textAlign = TextAlign.Center,
//                    fontSize = 20.sp,
//                    text = "Install",
//                    color = Color.Black,
//                )
//            }
//        }
////        items.forEach { blockVo ->
////            when (blockVo) {
////                is HeaderBlockVo -> item { HeaderBlock(blockVo) }
////                is TagBlockVo -> item { TagsBlock(blockVo) }
////                is RatingVo -> item { RatingBlock(blockVo) }
////                is DescriptionVo -> item { DescriptionBlock(blockVo) }
////                is CommentsBlockVo -> item { CommentsBlock(blockVo) }
////                else -> {}
////            }
////        }
//    }
//
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black,
    ) {
        val viewModel = DotaViewModel()
        DotaTheme {
            DotaGameScreen(viewModel)
        }
    }
}