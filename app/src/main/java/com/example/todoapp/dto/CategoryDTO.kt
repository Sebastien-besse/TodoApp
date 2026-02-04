package com.example.todoapp.dto

import androidx.compose.ui.graphics.Color
import com.example.todoapp.Model.Category
import com.example.todoapp.R
import com.example.todoapp.ui.theme.BlueCategoryBackground
import com.example.todoapp.ui.theme.CyanCategoryBackground
import com.example.todoapp.ui.theme.GreenCategoryBackground
import com.example.todoapp.ui.theme.GreenDarkCategoryBackground
import com.example.todoapp.ui.theme.OrangeCategoryBackground
import com.example.todoapp.ui.theme.PinkCategoryBackground
import com.example.todoapp.ui.theme.PinkDarkCategoryBackground
import com.example.todoapp.ui.theme.PurpleCategoryBackground
import com.example.todoapp.ui.theme.RedCategoryBackground
import com.example.todoapp.ui.theme.TurquoiseCategoryBackground

data class CategoryDTO(
    val image: String,
    val name: String,
    val color: String
) {

    fun toCategory(): Category {

        // 1️⃣ Mapping image
        val resId = when (image.lowercase()) {
            "grocery" -> R.drawable.grocery
            "work" -> R.drawable.work
            "sport" -> R.drawable.sport
            "design" -> R.drawable.design
            "university" -> R.drawable.university
            "social" -> R.drawable.social
            "music" -> R.drawable.music
            "health" -> R.drawable.health
            "movie" -> R.drawable.movie
            "home" -> R.drawable.home
            else -> R.drawable.grocery
        }

        // 2️⃣ Try Firestore color first
        val firestoreColor = try {
            val hex = color.replace("0x", "#")
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }

        // 3️⃣ Fallback to your original mapping if Firestore fails
        val categoryColor = firestoreColor ?: when (name) {
            "Grocery" -> GreenCategoryBackground
            "Work" -> OrangeCategoryBackground
            "Sport" -> CyanCategoryBackground
            "Design" -> TurquoiseCategoryBackground
            "University" -> PurpleCategoryBackground
            "Social" -> PinkDarkCategoryBackground
            "Music" -> PinkCategoryBackground
            "Health" -> GreenDarkCategoryBackground
            "Movie" -> BlueCategoryBackground
            "Home" -> RedCategoryBackground
            else -> Color.Gray
        }

        return Category(
            image = resId,
            name = name,
            color = categoryColor
        )
    }
}

