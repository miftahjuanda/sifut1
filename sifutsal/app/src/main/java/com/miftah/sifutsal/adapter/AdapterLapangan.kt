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
import kotlinx.android.synthetic.main.activity_detail_lapangan.view.*
import kotlinx.android.synthetic.main.item_horizontal.view.*

class AdapterLapangan(var data: List<DataItem?>, val itemClick: ItemLapanganClick): RecyclerView.Adapter<AdapterLapangan.LapanganHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LapanganHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
        return LapanganHolder(view)
    }

    override fun onBindViewHolder(holder: LapanganHolder, position: Int) {
        val item = data?.get(position)
        var lapangan = item?.lapangan

        //holder.name.text = lapangan?.get(position)?.namaLapangan

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.futsal1).centerCrop()
        Glide.with(holder.itemView.context)
            .load(item?.lapangan?.get(position)?.foto1)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(
                Glide.with(holder.itemView.context)
                    .load(item?.lapangan?.get(position)?.foto1)
                    .apply(requestOptions)
            )
            .apply(requestOptions)
            .into(holder.img)

        holder.view.setOnClickListener {
            itemClick.onClick(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?: 0

    class LapanganHolder(val view: View): RecyclerView.ViewHolder(view) {

        val img = view.img_detail_lapangan
        val name = view.tv_name_lapangan

    }

    interface ItemLapanganClick {
        fun onClick(item: DataItem?)
    }
}