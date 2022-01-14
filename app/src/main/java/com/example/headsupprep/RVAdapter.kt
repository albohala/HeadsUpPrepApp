package com.example.headsupprep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupprep.databinding.ItemRowBinding

class RVAdapter(var celebInfo:ArrayList<CelebrityItem>):  RecyclerView.Adapter<RVAdapter.ItemViewHolder>(){
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val celebItem = celebInfo[position]

        holder.binding.apply {
            tvName.text = celebItem.name
            tvTabooOne.text = celebItem.taboo1
            tvTabooTwo.text = celebItem.taboo2
            tvTabooThree.text = celebItem.taboo3
        }
    }

    override fun getItemCount(): Int {
        return celebInfo.size
    }

    fun update(celebInfo: ArrayList<CelebrityItem>) {
        this.celebInfo = celebInfo
        notifyDataSetChanged()
    }
}