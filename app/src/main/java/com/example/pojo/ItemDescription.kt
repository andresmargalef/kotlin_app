package com.example.pojo

import android.os.Parcel
import android.os.Parcelable

class ItemDescription() : Parcelable {
    var text: String? = null
    var plainText: String? = null

    constructor(parcel: Parcel) : this() {
        text = parcel.readString()
        plainText = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeString(plainText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemDescription> {
        override fun createFromParcel(parcel: Parcel): ItemDescription {
            return ItemDescription(parcel)
        }

        override fun newArray(size: Int): Array<ItemDescription?> {
            return arrayOfNulls(size)
        }
    }

}