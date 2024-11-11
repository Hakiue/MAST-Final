package com.example.poe22

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poe22.databinding.ActivityMenuItemEntryBinding

class MenuItemEntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuItemEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuItemEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courses = listOf("Starters", "Mains", "Desserts")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.courseSpinner.adapter = spinnerAdapter

        binding.addButton.setOnClickListener {
            val dishName = binding.dishNameEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val course = binding.courseSpinner.selectedItem.toString()
            val price = binding.priceEditText.text.toString().toDoubleOrNull()

            if (dishName.isNotEmpty() && description.isNotEmpty() && price != null) {
                val menuItem = CulinaryMenuItem(dishName, description, course, price)
                HomeActivity.menuItems.add(menuItem)

                Toast.makeText(this, "Menu item added", Toast.LENGTH_SHORT).show()
                clearFields()

                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.removeButton.setOnClickListener {
            val intent = Intent(this, RemoveMenuItemActivity::class.java)
            startActivityForResult(intent, 2)
        }

        binding.viewMenuItemsButton.setOnClickListener {
            startActivity(Intent(this, EndSplash::class.java))
        }
    }

    private fun clearFields() {
        binding.dishNameEditText.text.clear()
        binding.descriptionEditText.text.clear()
        binding.priceEditText.text.clear()
        binding.courseSpinner.setSelection(0)
    }
}


