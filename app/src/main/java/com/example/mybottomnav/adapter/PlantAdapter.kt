package com.example.mybottomnav.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybottomnav.R
import com.example.mybottomnav.data.Plant
import com.example.mybottomnav.ui.Detail.DetailActivity

class PlantAdapter(private val listPlant: ArrayList<Plant>) : RecyclerView.Adapter<PlantAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var plantPhoto: ImageView = itemView.findViewById(R.id.plant_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_plants_name)
        var tvDesc: TextView = itemView.findViewById(R.id.tv_plants_desc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, desc)=listPlant[position]
        val limitedDesc = if (desc.length > 25) desc.substring(0, 20) + "..." else desc
        holder.plantPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDesc.text = limitedDesc

        holder.itemView.setOnClickListener {
            val moveToDetail = Intent (holder.itemView.context, DetailActivity::class.java)
            moveToDetail.putExtra("key_plant", listPlant[holder.adapterPosition])
            holder.itemView.context.startActivity(moveToDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Plant)
    }

    override fun getItemCount(): Int = listPlant.size


}