package com.rm.pagingtutorial.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rm.pagingtutorial.common.loadImage
import com.rm.pagingtutorial.databinding.RecyclerviewUsersBinding
import com.rm.pagingtutorial.domain.model.Passenger


class PassengerAdapter :
    PagingDataAdapter<Passenger, PassengerAdapter.PassengersViewHolder>(PassengersComparator) {
    override fun onBindViewHolder(holder: PassengerAdapter.PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengerAdapter.PassengersViewHolder {
        return PassengersViewHolder(
            RecyclerviewUsersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class PassengersViewHolder(private val binding: RecyclerviewUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPassenger(item: Passenger) = with(binding) {
            imageViewAirlinesLogo.loadImage(item.logo)
            textViewHeadquarters.text = item.headQuarter
            textViewNameWithTrips.text = "${item.name}, {${item.trips}}"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }

    }

}
