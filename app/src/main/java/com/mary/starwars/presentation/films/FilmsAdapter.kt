package com.mary.starwars.presentation.films

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseAdapter
import com.mary.starwars.presentation.utils.hide
import com.mary.starwars.presentation.utils.inflate
import com.mary.starwars.presentation.utils.show
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
            itemView.setOnClickListener { view ->
                if(adapterPosition in items.indices) {
                    view.findNavController().navigate(
                        FilmsFragmentDirections.actionFilmsFragmentToDetailFragment(
                            items[adapterPosition].id
                        )
                    )
                }
            }
        }

        override fun bind(model: FilmViewObject) = with(itemView){
            film_divider.apply {
                if(adapterPosition == items.size - 1)
                    hide()
                else show()
            }
            film_episode_tv.text = resources.getString(R.string.film_episode, model.episodeId)
            film_title_tv.text = model.title
        }
    }
}