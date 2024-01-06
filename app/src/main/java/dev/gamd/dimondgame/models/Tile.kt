package dev.gamd.dimondgame.models

data class Tile(
    val id : Int,
    val image : Int,
    var isPairFound : Boolean,
    var isSelected : Boolean
)
