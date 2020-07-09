package com.gzeinnumer.viewbindingexamplekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gzeinnumer.viewbindingexamplekt.databinding.ItemAdapterRvBinding
import java.util.*

class AdapterRVMultiType : RecyclerView.Adapter<ViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == TYPE_NORMAL) {
            return MyHolder(
                ItemAdapterRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            return MyHolder(
                ItemAdapterRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_NORMAL) {
            (holder as MyHolder).bindData(list[position], onClick)
        } else {
            throw IllegalStateException("Unexpected value: " + holder.itemViewType)
        }
    }

    override fun getItemCount(): Int {
        return list.size.coerceAtLeast(0)
    }

    private val TYPE_NORMAL = 1

    override fun getItemViewType(position: Int): Int {
        return if (position != -1) {
            TYPE_NORMAL
        } else {
            0
        }
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String, onClick: MyOnClick?) {
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