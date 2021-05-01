package com.pawan.places.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.pawan.places.R
import com.pawan.places.db.model.ImageData
import kotlinx.android.synthetic.main.recycle_item.view.*


class GridRecycleAdapter(private val c: Context, private val images: List<ImageData>) :
    RecyclerView.Adapter<GridRecycleAdapter.ColorViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            LayoutInflater.from(c)
                .inflate(R.layout.recycle_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images.get(position)

        Glide.with(c)
            .load(path.download_url)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            //handle click event on image
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.img as ImageView
    }
}
