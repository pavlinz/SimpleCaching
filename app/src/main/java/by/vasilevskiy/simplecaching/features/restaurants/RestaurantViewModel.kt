package by.vasilevskiy.simplecaching.features.restaurants

import androidx.lifecycle.*
import by.vasilevskiy.simplecaching.api.RestaurantApi
import by.vasilevskiy.simplecaching.data.Restaurant
import by.vasilevskiy.simplecaching.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    repository: RestaurantRepository
) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()
}