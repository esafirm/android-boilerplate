package com.klikacc.android.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import java.util.*

abstract class BaseListAdapter<T>(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<T>()

    protected val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    protected var onItemClickListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemView != null) {
            onItemClickListener?.let { listener ->
                holder.itemView.setOnClickListener { listener.onItemClick(holder.adapterPosition) }
            }
        }
        onBind(holder, position)
    }

    protected abstract fun onBind(holder: RecyclerView.ViewHolder, position: Int)

    fun pushData(data: List<T>, isFirstPage: Boolean = true) {
        if (isFirstPage) {
            this.data.clear()
        }
        this.data.addAll(data)
    }

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): T {
        return data[position]
    }
}
