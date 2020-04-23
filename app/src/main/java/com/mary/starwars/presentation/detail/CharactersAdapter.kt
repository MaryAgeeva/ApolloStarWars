package com.mary.starwars.presentation.detail

import android.view.View
import android.view.ViewGroup
import com.mary.starwars.R
import com.mary.starwars.core.presentation.BaseAdapter
import com.mary.starwars.presentation.utils.inflate
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
            character_name_tv.text = model.name
            character_species_tv.text = model.species
        }
    }
}