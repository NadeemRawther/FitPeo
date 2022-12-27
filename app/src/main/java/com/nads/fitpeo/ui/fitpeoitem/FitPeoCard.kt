package com.nads.fitpeo.ui


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nads.fitpeo.model.FitPeoResponseItem
import com.nads.fitpeo.ui.fitpeoitem.FitPeoViewModel


@Composable
fun FitPeoCard(
    fitPeoItem: FitPeoResponseItem,
    fitPeoViewModel: FitPeoViewModel,
    navController: NavHostController
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 10.dp)
        .clip(
            shape = RoundedCornerShape(
                10.dp
            )
        )
        .clickable {
            fitPeoViewModel.getFitPeoItem(fitPeoItem.id.toString())
            navController.navigate("detail_screen")
        }
        .background(Color(0xfffdedec))
        .padding(10.dp)
        .semantics {
            testTag = fitPeoItem.id.toString()
        }) {
        Row() {
            AsyncImage(
                model = fitPeoItem.thumbnailUrl,
                contentDescription = "just a profile avatar image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {

                Text(
                    text = fitPeoItem.title, Modifier.fillMaxWidth()
                )
            }
        }
    }
}
@Composable
fun ProgressBars(enable:Boolean) {
    val progressValue = 0.99f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(900))
    )


    if (enable) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(progress = progressAnimationValue)

        }
    }


}


