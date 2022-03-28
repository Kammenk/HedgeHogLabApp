package com.example.hedgehoglabapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.hedgehoglabapp.repository.details.DetailsRepository
import com.example.hedgehoglabapp.ui.favorites.FavoritesViewModel
import com.example.hedgehoglabapp.ui.snackbar.CustomSnackBar
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {

    lateinit var viewModel: FavoritesViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var mockRepo: DetailsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = FavoritesViewModel(
            detailsRepository = mockRepo
        )
    }


    @Test
    fun `ensure snackbar is shown`() {
        val observer = mockk<Observer<CustomSnackBar>>(relaxed = true)
        viewModel.customSnackBarLiveData.observeForever(observer)

        viewModel.showSnackBar(123)

        verify { observer.onChanged(CustomSnackBar(123)) }
    }
}