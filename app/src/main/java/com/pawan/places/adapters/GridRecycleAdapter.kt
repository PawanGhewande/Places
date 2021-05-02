package com.pawan.places.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

        val requestOption = RequestOptions()
            .placeholder(R.drawable.ic_placeholder).fitCenter()

        Glide.with(c)
            .load(path.download_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(Glide.with(c)
                .load("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.daouidc.com%2Fresources%2Fimages%2Fcommon%2Floading.gif&f=1&nofb=1")
                .apply(requestOption))
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            //handle click event on image
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.img as ImageView
    }
}
