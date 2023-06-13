package com.example.mybottomnav.data.remote.recomendation

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlantsResponse(

	@field:SerializedName("plants")
	val plants: List<PlantsItem?>? = null
) : Parcelable

@Parcelize
data class PlantsItem(

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("care_level")
	val careLevel: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
