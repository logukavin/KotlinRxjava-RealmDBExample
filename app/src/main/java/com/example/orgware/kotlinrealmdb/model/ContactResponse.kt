package com.example.orgware.kotlinrealmdb.model

import com.google.gson.annotations.SerializedName

data class ContactResponse(

	@field:SerializedName("contacts")
	val contacts: List<ContactsItem?>? = null
)