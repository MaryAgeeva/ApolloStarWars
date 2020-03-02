package com.mary.starwars.presentation.films

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseAdapter
import com.mary.starwars.presentation.utils.inflate
import kotlinx.android.synthetic.main.item_film.view.*

class FilmsAdapter : BaseAdapter<FilmViewObject, FilmsAdapter.FilmsViewHolder>() {

    override fun setList(newItems: List<FilmViewObject>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(parent.inflate(R.layout.item_film))
    }

    inner class FilmsViewHolder(itemView: View) : BaseAdapter.BaseViewHolder<FilmViewObject>(itemView) {

        init {
            itemView.setOnClickListener {
                if(adapterPosition in items.indices) {
                    Navigation.createNavigateOnClickListener(
                        FilmsFragmentDirections.actionFilmsFragmentToDetailFragment(
                            items[adapterPosition].id
                        )
                    )
                }
            }
        }

        override fun bind(model: FilmViewObject) = with(itemView){
            film_episode_tv.text = resources.getString(R.string.film_episode, model.episodeId)
            film_title_tv.text = model.title
        }
    }
}