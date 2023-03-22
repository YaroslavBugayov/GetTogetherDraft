package com.bobrbolt.gettogether.presentation.models

import com.bobrbolt.gettogether.presentation.fragments.items.PostImageFragment

data class PostModel(
    val authorUsername: String,
    val images: Collection<String>,
    val description: String,
    val tagged: Collection<String>
    )
