package com.example.poe22

class CulinaryMenuItem(
    val dishName: String = "",
    val description: String = "",
    val course: String = "",
    val price: Double = 0.0
) {
    companion object {
        val menuItems = mutableListOf<CulinaryMenuItem>() // In-memory data
    }
}
