package com.miftah.sifutsal.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.miftah.sifutsal.R
import com.miftah.sifutsal.Response.DataItem
import com.miftah.sifutsal.Response.ResponseFutsal
import com.miftah.sifutsal.adapter.AdapterLapangan
import com.miftah.sifutsal.ui.detail.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.tv_fasilitas_detail
import kotlinx.android.synthetic.main.activity_detail.tv_jam_tutup
import kotlinx.android.synthetic.main.activity_detail_lapangan.*

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel
    var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getData()

        setSupportActionBar(toolbar_detail)
        supportActionBar.apply {
            title = "Detail"
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)

        }

        attachObserve()
        initData()

    }

    private fun attachObserve() {
        viewModel.responseData.observe(this, Observer { onSuccess(it) })
        viewModel.isError.observe(this, Observer { msgError(it) })
    }

    private fun msgError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(response: ResponseFutsal?) {
        val adapter = response?.data?.let {
            AdapterLapangan(it, object : AdapterLapangan.ItemLapanganClick {
                override fun onClick(item: DataItem?) {
                    Toast.makeText(applicationContext, "toassss", Toast.LENGTH_SHORT).show()
                }

            })
        }
        rv_lapangan.adapter = adapter
    }

    private fun initData() {
        val getData = intent.getParcelableExtra<DataItem?>("data")
        val lapangan = getData?.lapangan


        tv_name_detail.setText(getData?.nama)
        tv_alamat_detail.setText(getData?.alamat)
        tv_jam_buka.setText(getData?.jamBuka)
        tv_jam_tutup.setText(getData?.jamTutup)
        tv_alamat_detail2.setText(getData?.alamat)
        tv_fasilitas_detail.setText(Html.fromHtml(getData?.fasilitas))

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.futsal1).centerCrop()
        Glide.with(applicationContext)
            .load(getData?.foto)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(
                Glide.with(applicationContext)
                    .load(getData?.foto)
                    .apply(requestOptions)
            )
            .apply(requestOptions)
            .into(iv_detail)

        fab_detail.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getData?.telepon))
            startActivity(intent)
        }

    }

}