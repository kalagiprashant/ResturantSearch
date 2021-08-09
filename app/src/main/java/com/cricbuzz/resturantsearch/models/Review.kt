package com.cricbuzz.resturantsearch.models

data class Review(
    val comments: String,
    val date: String,
    val name: String,
    val rating: Int
)