package com.nads.fitpeo.ui

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import com.nads.fitpeo.FitPeo
import com.nads.fitpeo.FitPeoNavHost
import com.nads.fitpeo.FitPeoScreen
import com.nads.fitpeo.MainActivity
import com.nads.fitpeo.data.repo.FitPeoFakeRepositories
import com.nads.fitpeo.ui.fitpeoitem.FitPeoViewModel
import com.nads.fitpeo.ui.theme.FitPeoTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


@SdkSuppress(minSdkVersion = 18)
@HiltAndroidTest
class FitPeoComposeTest {
    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()
    @get:Rule(order = 3)
    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule(order = 4)
//    val activitytestrule = createAndroidComposeRule<MainActivity>()

    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext


    lateinit var navController: TestNavHostController



    private lateinit var fitPeoViewModel : FitPeoViewModel





    @Before
    fun setupAppNavHost() {
        hiltRule.inject()

        fitPeoViewModel = FitPeoViewModel(FitPeoFakeRepositories())

//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
//            FitPeoNavHost(navController = navController, fitPeoViewModel = fitPeoViewModel)
//        }
    }


    @Test
    fun myTest() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            FitPeoTheme(){
                FitPeoScreen(
                    navController = navController,

                    fitPeoViewModel = fitPeoViewModel,
                    paddingValues = PaddingValues(10.dp)
                    )
            }
        }
        //   composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("1").assertExists()
        composeTestRule.onNodeWithTag("1").performScrollTo()
        composeTestRule.onNodeWithTag("5").assertIsDisplayed()

    }

    @Test
    fun onRecreation_stateIsRestored() {
        val restorationTester = StateRestorationTester(composeTestRule)

        restorationTester.setContent {
            FitPeoScreen(
            PaddingValues(10.dp),
            fitPeoViewModel = fitPeoViewModel,
            navController

    )
    }

        composeTestRule.onNodeWithTag("1").performScrollTo()

        restorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithTag("5").assertExists()
        
    }

    @Test
    fun onScreenChange_test(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            FitPeoNavHost(navController = navController, fitPeoViewModel = fitPeoViewModel)
        }

        composeTestRule.onNodeWithTag("start").assertExists()
        composeTestRule.onNodeWithTag("1").performClick()
        composeTestRule.onNodeWithTag("MainScreen").assertIsDisplayed()


    }

}