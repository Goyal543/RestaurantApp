package com.example.restaurants.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.R
import com.example.restaurants.databinding.ItemRestaurantBinding
import com.example.restaurants.domain.models.RestaurantDomainModel

class RestaurantListAdapter(
    val onItemClick: (RestaurantDomainModel) -> Unit,
    val onDislikeClick: (RestaurantDomainModel) -> Unit
) : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private val restaurantsList = mutableListOf<RestaurantDomainModel>()

    fun updateList(list: List<RestaurantDomainModel>) {
        restaurantsList.clear()
        restaurantsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantDomainModel) {
            binding.tvRestaurantNameValue.text = restaurant.name
            binding.tvRestaurantAddressValue.text = restaurant.address
            binding.tvRestaurantCategoryValue.text = restaurant.categories

            if (restaurant.isDisliked) {
                binding.ivRestaurantDislike.setImageResource(R.drawable.ic_disliked_filled)
            } else {
                binding.ivRestaurantDislike.setImageResource(R.drawable.ic_disliked_default)
            }

            binding.ivRestaurantReview.setOnClickListener {
                onItemClick.invoke(restaurant)
            }

            binding.ivRestaurantDislike.setOnClickListener {
                onDislikeClick.invoke(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = restaurantsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurantsList[position])
    }
}
