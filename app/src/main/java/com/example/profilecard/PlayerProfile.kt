package com.example.profilecard

data class PlayerProfile constructor(val name: String, val status: Boolean, val drawable: Int)

val playerProfileList = arrayListOf(
    PlayerProfile("Jorginho Weak", false, R.drawable.pic1),
    PlayerProfile("Mason Mount", true, R.drawable.pic2),
)