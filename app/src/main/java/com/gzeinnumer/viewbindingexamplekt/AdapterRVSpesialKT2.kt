package com.gzeinnumer.viewbindingexamplekt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gzeinnumer.viewbindingexamplekt.databinding.ItemAdapterRvBinding
import java.util.*


class AdapterRVSpesialKT2(private var listener: (String) -> Unit) : RecyclerView.Adapter<AdapterRVSpesialKT2.MyHolder>() {
    var list: MutableList<String> = arrayListOf()

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
        holder.bindData(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size.coerceAtLeast(0)
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(
            data: String,
            listener: (String) -> Unit
        ) {
            binding.text.text = data
            binding.text.setOnClickListener {
                listener(data)
            }
        }
    }
}