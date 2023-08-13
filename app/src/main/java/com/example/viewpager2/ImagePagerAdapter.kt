package com.example.viewpager2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2.databinding.ItemImageLayoutBinding

class ImagePagerAdapter(private val imageList:ArrayList<ImageItem>):RecyclerView.Adapter<ImagePagerAdapter.ViewHolder>()  {


    class ViewHolder(val binding:ItemImageLayoutBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textView.text=imageList[position].desc
            imageView.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, imageList[position].image))
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}
