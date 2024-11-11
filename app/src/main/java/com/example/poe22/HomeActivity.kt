package com.example.poe22

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poe22.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: MenuListAdapter

    companion object {
        val menuItems = mutableListOf<CulinaryMenuItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        updateTotalMenuItems()
        displayAveragePriceByCourse()

        binding.addMenuItemButton.setOnClickListener {
            val intent = Intent(this, MenuItemEntryActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.filterMenuButton.setOnClickListener {
            val intent = Intent(this, LastSplash::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MenuListAdapter(menuItems)
        binding.recyclerView.adapter = adapter
    }

    private fun calculateAveragePriceByCourse(): Map<String, Double> {
        val coursePrices = mutableMapOf<String, MutableList<Double>>()
        for (item in menuItems) {
            if (!coursePrices.containsKey(item.course)) {
                coursePrices[item.course] = mutableListOf()
            }
            coursePrices[item.course]?.add(item.price)
        }
        return coursePrices.mapValues { (_, prices) ->
            prices.sum() / prices.size
        }
    }

    private fun displayAveragePriceByCourse() {
        val averagePrices = calculateAveragePriceByCourse()
        binding.averagePricesTextView.text = averagePrices.entries.joinToString("\n") {
            "${it.key}: ${String.format("%.2f", it.value)}"
        }
    }

    private fun updateTotalMenuItems() {
        binding.totalMenuItemsTextView.text = "Total Menu Items: ${menuItems.size}"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            updateTotalMenuItems()
            displayAveragePriceByCourse()
            adapter.notifyDataSetChanged() // Ensure the adapter is notified of data changes
        }
    }
}


