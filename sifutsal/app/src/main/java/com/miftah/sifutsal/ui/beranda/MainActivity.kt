package com.miftah.sifutsal.ui.beranda

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.miftah.sifutsal.R
import com.miftah.sifutsal.Response.DataItem
import com.miftah.sifutsal.Response.ResponseFutsal
import com.miftah.sifutsal.adapter.AdapterMain
import com.miftah.sifutsal.model.Image
import com.miftah.sifutsal.ui.beranda.viewModel.MainViewModel
import com.miftah.sifutsal.ui.detail.DetailActivity
import com.miftah.sifutsal.utils.Tools
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private var viewPager: ViewPager? = null
    private var layout_dots: LinearLayout? = null
    private var adapterImageSlider: AdapterImageSlider? = null
    private var runnable: Runnable? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData()

        attachObserve()
        initComponent()
    }

    private fun attachObserve() {
        viewModel.responseData.observe(this, androidx.lifecycle.Observer { onSuccess(it) })
        viewModel.isError.observe(this, androidx.lifecycle.Observer { msgError(it) })
    }

    private fun msgError(it: Throwable?) {
        Toast.makeText(this, it?.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(response: ResponseFutsal?) {
        val adapter = response?.data?.let { it ->
            AdapterMain(it, object : AdapterMain.ItemClick {
                override fun onClick(item: DataItem?) {
                    val intent = Intent(applicationContext, DetailActivity::class.java)
                    intent.putExtra("data", item)

                    startActivity(intent)
                }
            })

        }
        rv_list.adapter = adapter
    }


    private fun initComponent() {
        layout_dots = findViewById<View>(R.id.layout_dots) as LinearLayout
        viewPager = findViewById<View>(R.id.pager) as ViewPager
        adapterImageSlider = AdapterImageSlider(this, ArrayList())
        val items: MutableList<Image> = ArrayList()
        for (i in array_image_place.indices) {
            val obj = Image()
            obj.image = array_image_place[i]
            obj.imageDrw = resources.getDrawable(obj.image)
            items.add(obj)
        }
        adapterImageSlider?.setItems(items)
        viewPager?.adapter = adapterImageSlider

        // displaying selected image first
        viewPager?.currentItem = 0
        addBottomDots(layout_dots, adapterImageSlider!!.count, 0)
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                pos: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(pos: Int) {
                adapterImageSlider?.count?.let { addBottomDots(layout_dots, it, pos) }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        startAutoSlider(adapterImageSlider!!.count)
    }


    private fun addBottomDots(layout_dots: LinearLayout?, size: Int, current: Int) {
        val dots = arrayOfNulls<ImageView>(size)
        layout_dots?.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]?.layoutParams = params
            dots[i]?.setImageResource(R.drawable.shape_circle_outline)
            layout_dots?.addView(dots[i])
        }
        if (dots.size > 0) {
            dots[current]?.setImageResource(R.drawable.shape_circle)
        }
    }

    private fun startAutoSlider(count: Int) {
        runnable = Runnable {
            var pos = viewPager!!.currentItem
            pos = pos + 1
            if (pos >= count) pos = 0
            viewPager?.currentItem = pos
            handler.postDelayed(runnable, 3000)
        }
        handler.postDelayed(runnable, 3000)
    }

    private class AdapterImageSlider  // constructor
        (private val act: Activity, private var items: List<Image>) : PagerAdapter() {
        private var onItemClickListener: OnItemClickListener? = null

        private interface OnItemClickListener {
            fun onItemClick(view: View?, obj: Image?)
        }

        fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
            this.onItemClickListener = onItemClickListener
        }

        override fun getCount(): Int {
            return items.size
        }

        fun getItem(pos: Int): Image {
            return items[pos]
        }

        fun setItems(items: List<Image>) {
            this.items = items
            notifyDataSetChanged()
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as RelativeLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val o = items[position]
            val inflater = act.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v = inflater.inflate(R.layout.item_slider_image, container, false)
            val image = v.findViewById<View>(R.id.image) as ImageView
            Tools.displayImageOriginal(act, image, o.image)

            (container as ViewPager).addView(v)
            return v
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            (container as ViewPager).removeView(`object` as RelativeLayout)
        }
    }

    companion object {
        private val array_image_place = intArrayOf(
            R.drawable.image_12,
            R.drawable.image_13,
            R.drawable.image_14,
            R.drawable.image_15
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }
}