package com.akondi.cleanarchitecure.merchants.presentation.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akondi.cleanarchitecure.R
import com.akondi.cleanarchitecure.core.extensions.inflate
import com.akondi.cleanarchitecure.core.extensions.loadFromUrl
import com.akondi.cleanarchitecure.core.navigation.Navigator
import com.akondi.cleanarchitecure.merchants.domain.entities.merchants.MerchantView
import kotlinx.android.synthetic.main.row_merchant.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MerchantsAdapter
@Inject constructor() : RecyclerView.Adapter<MerchantsAdapter.ViewHolder>() {

    internal var collection: List<MerchantView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (MerchantView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent.inflate(
                R.layout.row_merchant
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(merchantView: MerchantView, clickListener: (MerchantView, Navigator.Extras) -> Unit) {
            itemView.merchantName.text = merchantView.name
            itemView.merchantPoster.loadFromUrl(merchantView.url)
            itemView.setOnClickListener { clickListener(merchantView, Navigator.Extras(itemView.merchantPoster)) }
        }
    }
}