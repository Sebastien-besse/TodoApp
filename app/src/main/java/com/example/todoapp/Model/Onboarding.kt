package com.example.todoapp.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.todoapp.R

data class Onboarding (
    @get:DrawableRes val image: Int,
    @get:StringRes val title: Int,
    @get:StringRes val content: Int,
){
    companion object{
        val pages = listOf(
            Onboarding(R.drawable.picture_onboarding1, R.string.onboarding_title_manage, R.string.onboarding_content_manage),
            Onboarding(R.drawable.picture_onboarding2, R.string.onboarding_title_create, R.string.onboarding_content_create),
            Onboarding(R.drawable.picture_onboarding3, R.string.onboarding_title_orgonaize, R.string.onboarding_content_orgonaize),
        )
    }
}
enum class OnboardingItems(val item: Onboarding){
    Manage(Onboarding.pages[0]),
    Create(Onboarding.pages[1]),
    Organize(Onboarding.pages[2])
}


