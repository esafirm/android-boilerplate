package com.esafirm.androidboilerplate.features.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esafirm.androidboilerplate.R
import com.esafirm.androidboilerplate.data.model.Ribot
import kotlinx.android.synthetic.main.item_ribot.view.*
import java.util.*
import javax.inject.Inject

class RibotsAdapter
@Inject constructor() : RecyclerView.Adapter<RibotsAdapter.RibotViewHolder>() {

	private var mRibots: List<Ribot>? = null

	init {
		mRibots = ArrayList<Ribot>()
	}

	fun setRibots(ribots: List<Ribot>) {
		mRibots = ribots
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RibotViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ribot, parent, false)
		return RibotViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: RibotViewHolder, position: Int) {
		val ribot = mRibots!![position]
		val name = ribot.profile.name

		holder.itemView.view_hex_color.setBackgroundColor(Color.parseColor(ribot.profile.hexColor))
		holder.itemView.text_name.text = String.format("%s %s", name.first, name.last)
		holder.itemView.text_email.text = ribot.profile.email
	}

	override fun getItemCount(): Int {
		return mRibots!!.size
	}

	inner class RibotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
