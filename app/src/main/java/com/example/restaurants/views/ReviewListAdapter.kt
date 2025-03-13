package com.example.restaurants.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.databinding.ItemReviewBinding
import com.example.restaurants.domain.models.ReviewDomainModel

class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {

    private val reviewList = mutableListOf<ReviewDomainModel>()
    private lateinit var binding: ItemReviewBinding

    fun updateList(list: List<ReviewDomainModel>) {
        reviewList.clear()
        reviewList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(reviewDetails: ReviewDomainModel) {
            binding.tvReviewBy.text = reviewDetails.reviewBy
            binding.tvReviewText.text = reviewDetails.reviewText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        holder.bind(reviewList[index])
    }
}