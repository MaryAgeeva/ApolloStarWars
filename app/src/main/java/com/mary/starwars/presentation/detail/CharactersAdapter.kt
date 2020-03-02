package com.mary.starwars.presentation.detail

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseAdapter
import com.mary.starwars.presentation.utils.hide
import com.mary.starwars.presentation.utils.inflate
import com.mary.starwars.presentation.utils.show
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter : BaseAdapter<CharacterViewObject, CharactersAdapter.CharacterViewHolder>() {

    override fun setList(newItems: List<CharacterViewObject>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(parent.inflate(R.layout.item_character))
    }

    inner class CharacterViewHolder(itemView: View) : BaseViewHolder<CharacterViewObject>(itemView) {

        override fun bind(model: CharacterViewObject) = with(itemView) {
            character_divider.apply {
                if(adapterPosition == items.size - 1)
                    hide()
                else show()
            }
            character_name_tv.text = model.name
            character_species_tv.text = model.species
            character_color_view.setBackgroundColor(ContextCompat.getColor(itemView.context, model.hairColor))
        }
    }
}