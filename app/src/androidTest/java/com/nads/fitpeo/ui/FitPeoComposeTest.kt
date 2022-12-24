package com.nads.fitpeo.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.nads.fitpeo.MainActivity
import com.nads.fitpeo.ui.theme.FitPeoTheme
import org.junit.Rule
import org.junit.Test

class FitPeoComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity
    @get:Rule
    val activitytestrule = createAndroidComposeRule<MainActivity>()


    @Test
    fun myTest() {
        ActivityScenario.launch(MainActivity::class.java).use {

        }
        composeTestRule.setContent {
            FitPeoTheme(){
                FitPeoCard()
            }
        }
        composeTestRule.onNodeWithTag("1").assertExists()
        composeTestRule.onNodeWithTag("1").performScrollTo()
        composeTestRule.onNodeWithTag("5").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Continue").performClick()
//
//        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }


    @Test
    fun `newTest`() {

        ActivityScenario.launch(MainActivity::class.java)
            .use { scenario ->
                scenario.onActivity { activity: MainActivity ->

                    composeTestRule
                        .onNodeWithTag("My Text").assertIsDisplayed()

                    activity.recreate()

                    composeTestRule
                        .onNodeWithTag("My Text").assertIsDisplayed()
                }
            }
    }
}