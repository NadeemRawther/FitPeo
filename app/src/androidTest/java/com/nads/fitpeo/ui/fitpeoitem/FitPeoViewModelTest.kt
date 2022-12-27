package com.nads.fitpeo.ui.fitpeoitem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FitPeoViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)
    @Before
    fun setUp() {
        hiltRule.inject()
    }
    @After
    fun tearDown() {
    }

    @Test
    fun getCards() {
    }

    @Test
    fun getLoading() {
    }

    @Test
    fun getOnline() {
    }

    @Test
    fun getError() {
    }

    @Test
    fun getFitPeoCardItem() {
    }

    @Test
    fun getFitPeoList() {
    }

    @Test
    fun getFitPeoItem() {
    }
}