package com.example.giffy.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giffy.R
import com.example.giffy.model.Image
import kotlinx.android.synthetic.main.item_my_shoes.view.*
import com.bumptech.glide.request.RequestOptions

class GiffySearchAdapter(val context: Context) : RecyclerView.Adapter<GiffySearchAdapter.ViewHolder>() {
    var gifs : List<Image> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

     fun update( status:  List<Image>) {
         gifs = status
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_my_shoes,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = gifs[position].url
        val options = RequestOptions()
        Glide.with(context)
            .asGif()
            .load(url)
            .apply(options.centerCrop())
            .into(holder.imageView);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.item_image_view
    }
}