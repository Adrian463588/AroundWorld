package com.adrian.justhelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.justhelloworld.databinding.ItemRowCountryBinding

class ListCountryAdapter(private val listCountry: ArrayList<Country>) :
    RecyclerView.Adapter<ListCountryAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCountry.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentCountry = listCountry[position]
        val (name, description, photo, uniquephoto) = listCountry[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_country", listCountry[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
        holder.binding.btnShare.setOnClickListener {
            val message = "Country Name :  ${currentCountry.name}"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.type = "text/plain"

            val shareIntentChooser = Intent.createChooser(shareIntent, null)
            holder.itemView.context.startActivity(shareIntentChooser)
        }
    }

    class ListViewHolder(var binding: ItemRowCountryBinding) : RecyclerView.ViewHolder(binding.root)
}