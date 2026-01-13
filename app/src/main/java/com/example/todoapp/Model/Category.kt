package com.example.todoapp.Model

import androidx.compose.ui.graphics.Color
import com.example.todoapp.ui.theme.BlueCategoryBackground
import com.example.todoapp.ui.theme.BlueCategoryFont
import com.example.todoapp.ui.theme.CyanCategoryBackground
import com.example.todoapp.ui.theme.CyanGreenCategoryFont
import com.example.todoapp.ui.theme.GreenCategoryBackground
import com.example.todoapp.ui.theme.GreenCategoryFont
import com.example.todoapp.ui.theme.GreenDarkCategoryBackground
import com.example.todoapp.ui.theme.GreenDarkCategoryFont
import com.example.todoapp.ui.theme.OrangeCategoryBackground
import com.example.todoapp.ui.theme.OrangeCategoryFont
import com.example.todoapp.ui.theme.PinkCategoryBackground
import com.example.todoapp.ui.theme.PinkCategoryFont
import com.example.todoapp.ui.theme.PinkDarkCategoryBackground
import com.example.todoapp.ui.theme.PinkDarkCategoryFont
import com.example.todoapp.ui.theme.PurpleBlueCategoryFont
import com.example.todoapp.ui.theme.PurpleCategoryBackground
import com.example.todoapp.ui.theme.RedCategoryBackground
import com.example.todoapp.ui.theme.RedCategoryFont
import com.example.todoapp.ui.theme.TurquoiseCategoryBackground
import com.example.todoapp.ui.theme.TurquoiseCategoryFont

enum class Category(val image: String, name: String, colorBackground: Color, colorFont: Color) {
    Grocery("", "Grocery", GreenCategoryBackground, GreenCategoryFont),
    Work("", "Work", OrangeCategoryBackground, OrangeCategoryFont),
    Sport("", "Sport", CyanCategoryBackground, CyanGreenCategoryFont),
    Design("", "Design", TurquoiseCategoryBackground, TurquoiseCategoryFont),
    University("", "University", PurpleCategoryBackground, PurpleBlueCategoryFont),
    Social("", "Social", PinkDarkCategoryBackground, PinkDarkCategoryFont),
    Music("", "Music", PinkCategoryBackground, PinkCategoryFont),
    Health("", "Health", GreenDarkCategoryBackground, GreenDarkCategoryFont),
    Movie("", "Movie", BlueCategoryBackground, BlueCategoryFont),
    Home("", "Home", RedCategoryBackground, RedCategoryFont)
}