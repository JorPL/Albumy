package com.jorpl.albumy.ui.album.master.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jorpl.albumy.databinding.ItemAlbumBinding
import com.jorpl.albumy.models.Album

class AlbumListAdapter(
    private val values: List<Album>,
    private val onClickListener: View.OnClickListener,
    private val onContextClickListener: View.OnContextClickListener
) :
    RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = "${item.title} - ${item.author}"
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setOnContextClickListener(onContextClickListener)
            }
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.idText
        val contentView: TextView = binding.content
    }

}