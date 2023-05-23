package com.example.mybottomnav.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plant(
    var photoUrl: String,
    var nama: String,
    var detail: String
) :Parcelable
