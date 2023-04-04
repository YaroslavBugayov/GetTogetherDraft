package com.bobrbolt.gettogether.presentation.models

data class PostModel(
    val authorUsername: String,
    val images: ArrayList<String>,
    val description: String,
    val tagged: ArrayList<String>
    )
