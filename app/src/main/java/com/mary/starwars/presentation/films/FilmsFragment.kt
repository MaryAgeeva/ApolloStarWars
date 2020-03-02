package com.mary.starwars.presentation.films

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseFragment
import com.mary.starwars.presentation.utils.hide
import com.mary.starwars.presentation.utils.show
import kotlinx.android.synthetic.main.films_fragment.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class FilmsFragment : BaseFragment() {

    private val filmsViewModel: FilmsViewModel by lifecycleScope.viewModel(this)
    private val filmsAdapter = FilmsAdapter()

    override val viewResource = R.layout.films_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        films_rv.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        films_refresh.setOnRefreshListener {
            filmsViewModel.getFilms()
        }

        filmsViewModel.films.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is FilmsState.Success -> showFilms(state.films)
                FilmsState.Loading -> showLoading()
                FilmsState.Error -> showError()
            }
        })
    }

    private fun showFilms(films: List<FilmViewObject>) {
        films_refresh.isRefreshing = false
        films_error_group.hide()
        films_pb.hide()
        filmsAdapter.setList(films)
        films_refresh.show()
    }

    private fun showLoading() {
        films_error_group.hide()
        films_pb.show()
    }

    private fun showError() {
        films_refresh.isRefreshing = false
        films_refresh.hide()
        films_pb.hide()
        films_error_group.show()
    }

    companion object {
        fun newInstance() = FilmsFragment()
    }
}
