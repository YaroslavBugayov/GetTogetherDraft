package com.bobrbolt.gettogether.presentation.models

import android.graphics.Bitmap

data class PostModel(
    val authorUsername: String,
    val images: ArrayList<Bitmap>,
    val description: String,
    val tagged: ArrayList<String>
    )
