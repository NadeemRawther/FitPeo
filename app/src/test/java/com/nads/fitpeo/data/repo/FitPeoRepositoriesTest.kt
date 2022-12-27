package com.nads.fitpeo.data.repo



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@HiltAndroidTest
class FitPeoRepositoriesTest {

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
    fun getFitPeoList() {
    }
    @Test
    fun getFitPeoListItem() {
    }
}