package com.incendiary.androidboilerplate.features.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.incendiary.androidboilerplate.R
import com.incendiary.androidboilerplate.data.model.Ribot
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

		holder.hexColorView!!.setBackgroundColor(Color.parseColor(ribot.profile.hexColor))
		holder.nameTextView!!.text = String.format("%s %s", name.first, name.last)
		holder.emailTextView!!.text = ribot.profile.email
	}

	override fun getItemCount(): Int {
		return mRibots!!.size
	}

	inner class RibotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		@BindView(R.id.view_hex_color) var hexColorView: View? = null
		@BindView(R.id.text_name) var nameTextView: TextView? = null
		@BindView(R.id.text_email) var emailTextView: TextView? = null

		init {
			ButterKnife.bind(this, itemView)
		}
	}
}
