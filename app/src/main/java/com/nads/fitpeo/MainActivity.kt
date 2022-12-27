package com.nads.fitpeo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nads.fitpeo.ui.FitPeoCard
import com.nads.fitpeo.ui.ProgressBars
import com.nads.fitpeo.ui.detail.DetailScreen
import com.nads.fitpeo.ui.fitpeoitem.FitPeoViewModel
import com.nads.fitpeo.ui.theme.FitPeoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val fitPeoViewModel:FitPeoViewModel  by viewModels()
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            FitPeoTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember{ SnackbarHostState() }
                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

                FitPeoNavHost(navController=navController,fitPeoViewModel)






            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitPeoNavHost(
    navController: NavHostController,
    fitPeoViewModel: FitPeoViewModel
) {
    NavHost(navController = navController, startDestination = "fit_peo_list"
    , modifier = Modifier.semantics { testTag = "start" }){

        composable("fit_peo_list"){
            FitPeo(navController,fitPeoViewModel)
        }
        composable("detail_screen"){
            DetailScreen(fitPeoViewModel,navController)
        }

    }

}




@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FitPeo(
    navController: NavHostController,

    fitPeoViewModel: FitPeoViewModel
) {

    Scaffold(
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            },
        topBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.secondary)) {
                Text(text = "FITPEO", modifier = Modifier.padding(top=20.dp),
                    fontWeight = FontWeight.Bold, style = TextStyle(
                        fontSize = 24.sp
                    )
                )
            }
        },
        content = { innerpadding ->
            var text by remember {
                mutableStateOf("")
            }
            fitPeoViewModel.getFitPeoList()
            FitPeoScreen(innerpadding,fitPeoViewModel,navController)

        },)

}



@Composable
fun FitPeoScreen(
    paddingValues: PaddingValues,
    fitPeoViewModel: FitPeoViewModel,
    navController: NavHostController
) {

    val loading by fitPeoViewModel.loading.collectAsState()
    if (loading) ProgressBars(true) else ProgressBars(false)
    val fitList by fitPeoViewModel.cards.collectAsState()
    Box(modifier = Modifier.padding(paddingValues)) {


        val listState = rememberLazyListState()
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp), state = listState,
            modifier = Modifier.padding(10.dp)) {
            items(fitList) { fitpeoItem ->
                FitPeoCard(
                 fitPeoItem = fitpeoItem
                ,fitPeoViewModel=fitPeoViewModel
                ,navController
                )
            }

        }

    }

}

