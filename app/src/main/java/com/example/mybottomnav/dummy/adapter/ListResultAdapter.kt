package com.example.mybottomnav.dummy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.R
import com.example.mybottomnav.dummy.data.Result

class ListResultAdapter(private val listResult: ArrayList<Result>): RecyclerView.Adapter<ListResultAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListResultAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_result_plant, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListResultAdapter.ListViewHolder, position: Int) {
        val (name, imgPhoto) = listResult[position]
        holder.tvResult.text = name
        holder.imgPhoto.setImageResource(imgPhoto!!)

        holder.itemView.setOnClickListener {
//            val intentDetail = Intent(holder.itemView.context, )
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Result)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvResult: TextView = itemView.findViewById(R.id.tv_result_name)
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_result)
    }
}