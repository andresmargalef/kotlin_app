package com.example.pojo

import android.os.Parcel
import android.os.Parcelable

class ItemDetail() : Parcelable {
    var id : String? = null
    var title: String? = null
    var price: Double = Double.NaN
    var thumbnail: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        price = parcel.readDouble()
        thumbnail = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDetail> {
        override fun createFromParcel(parcel: Parcel): ItemDetail {
            return ItemDetail(parcel)
        }

        override fun newArray(size: Int): Array<ItemDetail?> {
            return arrayOfNulls(size)
        }
    }

}