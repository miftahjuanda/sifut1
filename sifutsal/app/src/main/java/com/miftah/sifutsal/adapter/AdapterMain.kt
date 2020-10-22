package com.miftah.sifutsal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.miftah.sifutsal.R
import com.miftah.sifutsal.Response.DataItem
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterMain(var data: List<DataItem?>, val itemClick: ItemClick) :
    RecyclerView.Adapter<AdapterMain.FutsalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutsalHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return FutsalHolder(view)
    }

    override fun onBindViewHolder(holder: FutsalHolder, position: Int) {
        val item = data?.get(position)
        //val lapangan = item?.lapangan?.get(position)

        holder.name.text = item?.nama
        holder.alamat.text = item?.alamat
        //holder.alamat.text = lapangan?.jenisLantai

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.futsal1).centerCrop()
        Glide.with(holder.itemView.context)
            .load(item?.foto)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(
                Glide.with(holder.itemView.context)
                    .load(item?.foto)
                    .apply(requestOptions)
            )
            .apply(requestOptions)
            .into(holder.img)

        holder.view.setOnClickListener {
            itemClick.onClick(item)

        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class FutsalHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val img = view.iv_image
        val name = view.tv_nama
        val alamat = view.tv_alamat

    }

    interface ItemClick {
        fun onClick(item: DataItem?)
    }
}