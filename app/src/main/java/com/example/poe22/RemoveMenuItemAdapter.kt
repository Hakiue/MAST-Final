package com.example.poe22

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poe22.databinding.MenuItemBinding

class RemoveMenuItemAdapter(
    private val menuItems: List<CulinaryMenuItem>,
    private val onItemClick: (CulinaryMenuItem) -> Unit
) : RecyclerView.Adapter<RemoveMenuItemAdapter.MenuItemViewHolder>() {

    class MenuItemViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuItemBinding.inflate(inflater, parent, false)
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.binding.dishNameTextView.text = menuItem.dishName
        holder.binding.descriptionTextView.text = menuItem.description
        holder.binding.courseTextView.text = menuItem.course
        holder.binding.priceTextView.text = "$${menuItem.price}"

        holder.itemView.setOnClickListener {
            onItemClick(menuItem)
        }
    }

    override fun getItemCount(): Int = menuItems.size
}

