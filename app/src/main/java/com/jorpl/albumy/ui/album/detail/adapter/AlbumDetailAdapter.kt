package com.jorpl.albumy.ui.album.detail.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jorpl.albumy.R


class AlbumDetailAdapter internal constructor(context: Context, data: List<String>) :
    RecyclerView.Adapter<AlbumDetailAdapter.ViewHolder>() {
    private val mData: List<String>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    // inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mData[position])
            .into(holder.ivPhoto)
    }

    // total number of cells
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var ivPhoto: ImageView = itemView.findViewById(R.id.photo_photo)

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}