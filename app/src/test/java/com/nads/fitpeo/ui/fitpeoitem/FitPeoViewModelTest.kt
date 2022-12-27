package com.nads.fitpeo.ui.fitpeoitem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.nads.fitpeo.data.repo.FitPeoFakeRepositories
import dagger.hilt.android.testing.HiltAndroidRule
import com.google.common.truth.Truth.assertThat
import com.nads.fitpeo.getOrAwaitValue
import com.nads.fitpeo.model.FitPeoResponseItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@SmallTest
class FitPeoViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var fitPeoViewModel : FitPeoViewModel
    val dispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        fitPeoViewModel = FitPeoViewModel(FitPeoFakeRepositories())
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getFitPeoList() {
        runTest {
            launch(Dispatchers.Main) {

                fitPeoViewModel.getFitPeoList()
            }


        }
        val list  = fitPeoViewModel.cards.value
        assertThat(list).isEqualTo(fitpeoitemList)

    }

    @Test
    fun getFitPeoItem() {
        runBlocking {
            launch(Dispatchers.Main) {
                fitPeoViewModel.getFitPeoItem(0.toString())
            }

        }
        val listItem = fitPeoViewModel.fitPeoCardItem.value
        assertThat(listItem).isEqualTo(fitpeoitemList[0])
    }

    companion object{
        val fitpeoitemList = arrayListOf<FitPeoResponseItem>(
            FitPeoResponseItem(
                albumId= 1,
                id= 1,
                title= "accusamus beatae ad facilis cum similique qui sunt",
                url= "https://via.placeholder.com/600/92c952",
                thumbnailUrl= "https://via.placeholder.com/150/92c952"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 2,
                title= "reprehenderit est deserunt velit ipsam",
                url= "https://via.placeholder.com/600/771796",
                thumbnailUrl= "https://via.placeholder.com/150/771796"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 3,
                title= "officia porro iure quia iusto qui ipsa ut modi",
                url= "https://via.placeholder.com/600/24f355",
                thumbnailUrl= "https://via.placeholder.com/150/24f355"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 4,
                title= "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
                url= "https://via.placeholder.com/600/d32776",
                thumbnailUrl= "https://via.placeholder.com/150/d32776"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 5,
                title= "natus nisi omnis corporis facere molestiae rerum in",
                url= "https://via.placeholder.com/600/f66b97",
                thumbnailUrl= "https://via.placeholder.com/150/f66b97"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 6,
                title= "accusamus ea aliquid et amet sequi nemo",
                url= "https://via.placeholder.com/600/56a8c2",
                thumbnailUrl= "https://via.placeholder.com/150/56a8c2"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 7,
                title= "officia delectus consequatur vero aut veniam explicabo molestias",
                url= "https://via.placeholder.com/600/b0f7cc",
                thumbnailUrl= "https://via.placeholder.com/150/b0f7cc"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 8,
                title= "aut porro officiis laborum odit ea laudantium corporis",
                url= "https://via.placeholder.com/600/54176f",
                thumbnailUrl= "https://via.placeholder.com/150/54176f"
            ),
            FitPeoResponseItem(
                albumId= 1,
                id= 9,
                title= "qui eius qui autem sed",
                url= "https://via.placeholder.com/600/51aa97",
                thumbnailUrl= "https://via.placeholder.com/150/51aa97"
            ),
            FitPeoResponseItem(
                albumId=1,
                id=10,
                title="beatae et provident et ut vel",
                url= "https://via.placeholder.com/600/810b14",
                thumbnailUrl = "https://via.placeholder.com/150/810b14"
            )
        )
    }

}