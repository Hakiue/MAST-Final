package com.example.poe22

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.poe22.databinding.ActivityCourseFilterBinding

class CourseFilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCourseSpinner()

        binding.filterButton.setOnClickListener {
            val selectedCourse = binding.courseSpinner.selectedItem.toString()
            val filteredItems = HomeActivity.menuItems.filter { it.course == selectedCourse }

            binding.filteredItemsTextView.text = filteredItems.joinToString("\n") {
                "${it.dishName} - ${it.course} - ${String.format("%.2f", it.price)}"
            }
        }
    }

    private fun setupCourseSpinner() {
        val courses = listOf("Starters", "Mains", "Desserts")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.courseSpinner.adapter = spinnerAdapter
    }
}
