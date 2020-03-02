package com.mary.starwars.core.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<V, VH: BaseAdapter.BaseViewHolder<V>> : RecyclerView.Adapter<VH>() {

    protected var items = listOf<V>()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    abstract fun setList(newItems: List<V>)

    abstract class BaseViewHolder<V>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(model: V)
    }
}