package by.vasilevskiy.simplecaching.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.vasilevskiy.simplecaching.databinding.ActivityRestaurantBinding
import by.vasilevskiy.simplecaching.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {

    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@RestaurantActivity)
            }

            viewModel.restaurants.observe(this@RestaurantActivity,  Observer { result->
                restaurantAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            })
        }
    }
}