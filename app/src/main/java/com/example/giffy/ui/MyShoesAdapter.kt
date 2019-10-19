package com.example.giffy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giffy.R
import com.example.giffy.model.Image
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.item_my_shoes.view.*
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions



fun Context.dpToPx(dp: Float): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

class MyShoesAdapter(val context: Context) : RecyclerView.Adapter<MyShoesAdapter.ViewHolder>(),Filterable {

    var scanResult = mutableListOf<String>()
    var scanResultCopy = mutableListOf<String>()


    override fun getFilter(): Filter {

        return scanFilter
    }

    private val scanFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var filteredList = emptyList<String>()

            if (constraint == null || constraint.length < 1) {
                filteredList = scanResultCopy

            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()
                filteredList = scanResult.filter { it.contains(filterPattern) }
            }

            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            scanResult = results?.values as MutableList<String>
            notifyDataSetChanged()
        }
    }

    var gifs : List<Image> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var pairId: String? = null

     fun update( status:  List<Image>) {
         gifs = status
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_shoes, parent, false))
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val width = context.dpToPx(gifs[position].width.toFloat())
//        val height =  context.dpToPx(gifs[position].height.toFloat())
//        val parms = FrameLayout.LayoutParams(width, height)
//        holder.nickName.setLayoutParams(parms)

        val url = gifs[position].url

        val options = RequestOptions()
        Glide.with(context)
            .asGif()
            .load(url)
            .apply(options.centerCrop())
            .into(holder.nickName);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nickName: ImageView = itemView.item_image_view

    }
}