package com.example.vktestapplication.ui.main


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vktestapplication.R
import com.example.vktestapplication.data.GifClass
import com.example.vktestapplication.databinding.ItemLayoutBinding


class MainAdapter(context: Context, private val gifsOnClickListener: GifClickListener): PagingDataAdapter<GifClass, GifViewHolder>(GifDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(layoutInflater.inflate(R.layout.item_layout,
            parent, false), gifsOnClickListener)
    }


}





class GifViewHolder(itemView: View, private val gifsOnClickListener: GifClickListener) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding: ItemLayoutBinding = ItemLayoutBinding.bind(itemView)

    fun bind(gif: GifClass?) {
        with(viewBinding) {
            Glide.with(itemView).load(gif?.images?.original?.url).placeholder(R.drawable._15612_document_file_gif_icon).centerCrop().into(imageView)
            imageView.setOnClickListener {
                gifsOnClickListener.onGifClick(gif)
            }
        }
    }

}

private object GifDiffItemCallback : DiffUtil.ItemCallback<GifClass>() {

    override fun areItemsTheSame(oldItem: GifClass, newItem: GifClass): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GifClass, newItem: GifClass): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }
}

interface GifClickListener{

    fun onGifClick(gif: GifClass?)

}