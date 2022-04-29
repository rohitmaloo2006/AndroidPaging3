package com.rm.pagingtutorial.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rm.pagingtutorial.common.visible
import com.rm.pagingtutorial.databinding.LoadingErrorStateBinding
import retrofit2.HttpException
import java.io.IOException

class PassengersLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PassengersLoadStateAdapter.PassengerLoadStateViewHolder>() {

    inner class PassengerLoadStateViewHolder(
        private val binding: LoadingErrorStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                when (loadState.error) {
                    is HttpException -> {
                        binding.textViewError.text = loadState.error.localizedMessage
                    }
                    is IOException -> {
                        binding.textViewError.text = loadState.error.localizedMessage
                    }
                    else -> {
                        binding.textViewError.text = loadState.error.localizedMessage
                    }
                }

            }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textViewError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onBindViewHolder(holder: PassengerLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PassengerLoadStateViewHolder(
        LoadingErrorStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}
