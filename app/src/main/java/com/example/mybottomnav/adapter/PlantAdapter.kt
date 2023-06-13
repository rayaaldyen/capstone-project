package com.example.mybottomnav.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.R

class PlantAdapter(private var recommendations: List<String>) :
    RecyclerView.Adapter<PlantAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_plants_name)
        var ivPlant: ImageView = itemView.findViewById(R.id.iv_plant)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val recommendation = recommendations[position].replace("-", " ")

        holder.tvName.text = recommendation.split(" ").joinToString(" ") { it.capitalize() }
        if (recommendation.contains("cabai")) {
            holder.ivPlant.setImageResource(R.drawable.cabai)
        } else if (recommendation.contains("kentang")) {
            holder.ivPlant.setImageResource(R.drawable.kentang)
        } else if (recommendation.contains("kubis")) {
            holder.ivPlant.setImageResource(R.drawable.kubis)
        } else if (recommendation.contains("merah")) {
            holder.ivPlant.setImageResource(R.drawable.bawang_merah)
        } else if (recommendation.contains("putih")) {
            holder.ivPlant.setImageResource(R.drawable.bawang_putih)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(recommendation)
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }

    override fun getItemCount(): Int {
        return recommendations.size
    }

    fun setRecommendations(recommendations: List<String>) {
        this.recommendations = recommendations
        notifyDataSetChanged()
    }


}