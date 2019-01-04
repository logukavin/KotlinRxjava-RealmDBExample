package com.example.orgware.kotlinrealmdb.model

import com.google.gson.annotations.SerializedName

data class Phone(

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("office")
	val office: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)