package com.example.orgware.kotlinrealmdb.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orgware.kotlinrealmdb.R
import com.example.orgware.kotlinrealmdb.realmmodel.ContactList
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(var context: Context) : RecyclerView.Adapter<ListAdapter.ContactHolder>() {
    var contactsItem: List<ContactList>? = null

    init {
        contactsItem = ArrayList()
    }

    fun setFolderList(item: ArrayList<ContactList>?) {
        if (item == null)
            return
        contactsItem = item
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        return ContactHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return contactsItem!!.size
    }

    override fun onBindViewHolder(holder:ContactHolder, position: Int) {
        holder.bindData(contactsItem!![position])
    }

    inner class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var v: View = view

        fun bindData(item: ContactList) {
            v.tv_name.text = item.name
            v.tv_number.text = item.phone
        }

    }

}


