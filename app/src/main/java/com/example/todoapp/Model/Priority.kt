package com.example.todoapp.Model

enum class Priority( val level : Int) {
    Level1(1),
    Level2(2),
    Level3(3),
    Level4(4),
    Level5(5),
    Level6(6),
    Level7(7),
    Level8(8),
    Level9(9),
    Level10(10);

    companion object {
        fun fromLevel(value: Int): Priority {
            return values().find { it.level == value } ?: Level1
        }
    }
}