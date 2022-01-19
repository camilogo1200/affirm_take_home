package com.affirm.takehome.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.affirm.takehome.R
import com.affirm.takehome.databinding.RestaurantItemViewBinding
import com.affirm.takehome.domain.models.Restaurant
import com.squareup.picasso.Picasso

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val restaurantList = mutableListOf<Restaurant>()

    fun addRestaurants(restaurants: List<Restaurant>) {
        val oldPosition = this.restaurantList.size
        restaurantList.addAll(restaurants)
        notifyItemRangeChanged(oldPosition, restaurants.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RestaurantItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.restaurant_item_view, parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    class RestaurantViewHolder(private val binding: RestaurantItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurantNameTextView.text = restaurant.name
            binding.restaurantRatingTextView.text = restaurant.rating
            if (restaurant.image.isNotBlank()) {
                Picasso.get().load(restaurant.image).into(binding.imageView)
            }
        }
    }
}
