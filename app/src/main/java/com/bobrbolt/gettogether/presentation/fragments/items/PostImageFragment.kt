package com.bobrbolt.gettogether.presentation.fragments.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bobrbolt.gettogether.R

class PostImageFragment() : Fragment() {

    private lateinit var image: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_image, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(imageString: String) = PostImageFragment().apply {
            arguments = bundleOf(
                image to imageString
            )
        }
    }
}