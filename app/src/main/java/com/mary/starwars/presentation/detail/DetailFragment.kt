package com.mary.starwars.presentation.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseFragment
import org.koin.androidx.scope.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by lifecycleScope.viewModel(this) { parametersOf(args.id)}
    private val args : DetailFragmentArgs by navArgs()
    private val charAdapter = CharactersAdapter()

    override val viewResource = R.layout.detail_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        film_detail_chars_rv.apply {
            adapter = charAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        detailViewModel.film.observe(viewLifecycleOwner, Observer { film ->
            showFilmInfo(film)
        })
    }

    private fun showFilmInfo(film: FilmDetailViewObject) {
        film_detail_episode_tv.text = resources.getString(R.string.film_episode, film.episodeId)
        film_detail_title_tv.text = film.title
        film_detail_director_tv.text = film.director
        charAdapter.setList(film.characters)
    }

    companion object {
        fun newInstance() = DetailFragment()
    }
}
