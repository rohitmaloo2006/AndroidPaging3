package com.rm.pagingtutorial.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rm.pagingtutorial.app.adapter.PassengerAdapter
import com.rm.pagingtutorial.app.adapter.PassengersLoadStateAdapter
import com.rm.pagingtutorial.app.vm.PassengersViewModel
import com.rm.pagingtutorial.databinding.FragmentStockBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PassengerFragment : Fragment() {

    private val mListViewModel: PassengersViewModel by viewModels()


    private var _binding: FragmentStockBinding? = null
    private val mBinding: FragmentStockBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (_binding == null) {
            _binding = FragmentStockBinding.inflate(inflater)
        }
        //  initViews()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
    }

    private fun configureView() {
        var passengersAdapter = PassengerAdapter()
        mBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerview.setHasFixedSize(true)
        mBinding.recyclerview.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = PassengersLoadStateAdapter { passengersAdapter.retry() },
            footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
        )

        lifecycleScope.launchWhenStarted {
            mListViewModel.getPassengerData().collectLatest { listData ->
                passengersAdapter.submitData(listData)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}