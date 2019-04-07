package com.example.pojo

import android.os.Parcel
import android.os.Parcelable

data class Results(val siteId: String, val results: ArrayList<ItemDetail>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("results")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(siteId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Results> {
        override fun createFromParcel(parcel: Parcel): Results {
            return Results(parcel)
        }

        override fun newArray(size: Int): Array<Results?> {
            return arrayOfNulls(size)
        }
    }
}
