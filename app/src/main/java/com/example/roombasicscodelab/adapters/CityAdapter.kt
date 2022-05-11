package com.example.roombasicscodelab.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roombasicscodelab.R
import com.example.roombasicscodelab.entities.City

class CityAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cities = emptyList<City>()

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cityItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent,false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = cities[position]
        holder.cityItemView.text = current.city
    }

    internal fun setCities(cities: List<City>){
        this.cities = cities
        notifyDataSetChanged()
    }

    override fun getItemCount() = cities.size
}