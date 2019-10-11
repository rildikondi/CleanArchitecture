package com.akondi.cleanarchitecure.merchants.domain.entities.merchants

import android.os.Parcel
import com.akondi.cleanarchitecure.core.platform.KParcelable
import com.akondi.cleanarchitecure.core.platform.parcelableCreator


class MerchantView(
    val id: Int,
    val name: String?,
    val url: String?
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::MerchantView)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(name)
            writeString(url)
        }
    }
}