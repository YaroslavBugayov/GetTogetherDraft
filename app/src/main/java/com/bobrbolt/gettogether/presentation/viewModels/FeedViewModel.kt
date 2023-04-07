package com.bobrbolt.gettogether.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bobrbolt.gettogether.presentation.models.PostModel

class FeedViewModel : ViewModel() {
    private val _posts = MutableLiveData<ArrayList<PostModel>>()
    val posts: LiveData<ArrayList<PostModel>>
        get() = _posts

    init {
        // Додаби б, щоб коли з картинкою проблеми, то підгружало картинку зі сторейджа, що біда

        _posts.value = arrayListOf(
//            PostModel(
//                authorUsername = "username1",
//                images = arrayListOf(image, image),
//                description = "description1",
//                tagged = arrayListOf("tag1", "tag2")
//            ),
//            PostModel(
//                authorUsername = "username2",
//                images = arrayListOf(image),
//                description = "description2",
//                tagged = arrayListOf("tag3", "tag4")
//            ),
//            PostModel(
//                authorUsername = "username3",
//                images = arrayListOf(image, image),
//                description = "description3",
//                tagged = arrayListOf("tag3", "tag4")
//            ),
//            PostModel(
//                authorUsername = "username4",
//                images = arrayListOf(image, image, image, image),
//                description = "description4",
//                tagged = arrayListOf("tag3", "tag4")
//            )
        )
    }

//    fun addPost(post: PostModel) {
//        val updatesPost = _posts.value?.toMutableList() ?: mutableListOf()
//        updatesPost.add(post)
//        _posts.value = updatesPost
//    }
}