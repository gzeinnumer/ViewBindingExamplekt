package com.gzeinnumer.viewbindingexamplekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gzeinnumer.viewbindingexamplekt.databinding.ItemAdapterRvBinding
import java.util.*


class AdapterRV : RecyclerView.Adapter<AdapterRV.MyHolder>() {
    var list: MutableList<String> = arrayListOf()

    var onClick: MyOnClick? = null

    interface MyOnClick {
        fun myOnClick(position: Int)
    }

    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addList(data: String) {
        list.add(data)
        notifyItemChanged(list.size - 1) // untuk dinamis recyclerview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemAdapterRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bindData(list[position], onClick)
    }

    override fun getItemCount(): Int {
        return list.size.coerceAtLeast(0)
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(
            data: String,
            onClick: MyOnClick?
        ) {
            binding.text.text = data
            if (onClick != null) {
                binding.text.setOnClickListener(View.OnClickListener {
                    onClick.myOnClick(
                        adapterPosition
                    )
                })
            }
        }

    }
}