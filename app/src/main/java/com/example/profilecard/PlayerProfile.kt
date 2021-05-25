package com.example.profilecard

data class PlayerProfile constructor(val id: Int, val name: String, val status: Boolean, val drawable: Int)

val playerProfileList = arrayListOf(
    PlayerProfile(id = 0, name = "Jorginho Weak", false, R.drawable.pic1),
    PlayerProfile(id = 1, name = "Mason Mount", true, R.drawable.pic2),
    PlayerProfile(id = 2, name = "Eden Hazard", false, R.drawable.pic3),
    PlayerProfile(id = 3, name = "Andreas Christensen", true, R.drawable.pic4),
    PlayerProfile(id = 4, name = "Billy Gilmour", true, R.drawable.pic5),
    PlayerProfile(id = 5, name = "Ngolo Kante", true, R.drawable.pic6),
    PlayerProfile(id = 6, name = "Marqinhos", false, R.drawable.pic7),
    PlayerProfile(id = 7, name = "Douglas Luiz", false, R.drawable.pic8),
    PlayerProfile(id = 8, name = "Yves Bissouma", false, R.drawable.pic9),
    PlayerProfile(id = 9, name = "Luka Modric", true, R.drawable.pic10),

)