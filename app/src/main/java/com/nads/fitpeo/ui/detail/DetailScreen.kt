package com.nads.fitpeo.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nads.fitpeo.ui.ProgressBars
import com.nads.fitpeo.ui.fitpeoitem.FitPeoViewModel

@Composable
fun DetailScreen(fitPeoViewModel: FitPeoViewModel, navController: NavHostController) {
    val loading by fitPeoViewModel.loading.collectAsState()
    if (loading) {ProgressBars(true)}
    else{ ProgressBars(false)
        Column(modifier = Modifier.fillMaxSize().semantics {
            testTag = "MainScreen"
        }) {
            val fitItem by fitPeoViewModel.fitPeoCardItem.collectAsState()
            AsyncImage(
                model = fitItem.url,
                contentDescription = "PictureUrl",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 15.dp, end = 15.dp)
                    .height(height = 350.dp)

            )
            Text(
                text = fitItem.title,
                fontSize = 30.sp,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold
            )

        }
    }


}