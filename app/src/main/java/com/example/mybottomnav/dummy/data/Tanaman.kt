package com.example.mybottomnav.dummy.data

import android.os.Parcel
import android.os.Parcelable

data class Tanaman(
    val nama: String?,
    val angka: String?,
    ): Parcelable{
        constructor(parcel: Parcel): this(
            parcel.readString(),
            parcel.readString(),
        ){

        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(angka)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<Tanaman>{
        override fun createFromParcel(parcel: Parcel): Tanaman {
            return Tanaman(parcel)
        }

        override fun newArray(size: Int): Array<Tanaman?> {
            return arrayOfNulls(size)
        }
    }
    }
