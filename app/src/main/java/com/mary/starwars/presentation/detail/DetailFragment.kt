package com.mary.starwars.presentation.detail

import android.os.Bundle

import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    override val viewResource = R.layout.detail_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        fun newInstance() =
            DetailFragment()
    }
}
