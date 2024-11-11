package com.example.poe22

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poe22.databinding.ActivityRemoveMenuItemBinding

class RemoveMenuItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRemoveMenuItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoveMenuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RemoveMenuItemAdapter(HomeActivity.menuItems) { menuItem ->
            HomeActivity.menuItems.remove(menuItem)
            Toast.makeText(this, "${menuItem.dishName} removed", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish() // Close the activity after removal
        }
        recyclerView.adapter = adapter
    }
}


