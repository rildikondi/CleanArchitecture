package com.akondi.cleanarchitecure.merchants.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.core.extensions.loadFromUrl
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.Image
import kotlinx.android.synthetic.main.slide_layout.view.*

class MerchantDetailsAdapter(
    private var images: List<Image>,
    private val onPageClickListener: OnPageClickListener
) : PagerAdapter() {

    override fun getCount(): Int = images.size

    fun setImages(images: List<Image>) {
        this.images = images
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull o: Any): Boolean {
        return view === o
    }

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater =
            container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.slide_layout, container, false)
        view.imageView.loadFromUrl(images[position].url)
        view.setOnClickListener { onPageClickListener.onClick(position) }

        container.addView(view)
        return view
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}