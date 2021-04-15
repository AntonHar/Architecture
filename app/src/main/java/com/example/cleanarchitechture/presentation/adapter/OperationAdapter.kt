package com.example.cleanarchitechture.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitechture.R
import com.example.cleanarchitechture.domain.Operation
import com.example.cleanarchitechture.domain.entity.Person

class OperationAdapter internal constructor(
    private var data: List<Person>
) : RecyclerView.Adapter<OperationAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.operation_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.name.text = item.name
        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<Person>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_text)
    }

    fun setListener(itemClickListener: ItemClickListener?) {
        listener = itemClickListener
    }
}

interface ItemClickListener {
    fun onItemClick(person: Person)
}
