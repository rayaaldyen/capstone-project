package com.example.mybottomnav.dummy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.R
import com.example.mybottomnav.dummy.data.Tanaman

class ListTanamanAdapter(private val listTanaman: ArrayList<Tanaman>): RecyclerView.Adapter<ListTanamanAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_plant, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, percent) = listTanaman[position]
        holder.tvName.text = name
        holder.tvPercent.text = percent
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Tanaman)
    }

    override fun getItemCount(): Int = listTanaman.size

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_plant_name)
        val tvPercent: TextView = itemView.findViewById(R.id.tv_percent)
    }
}