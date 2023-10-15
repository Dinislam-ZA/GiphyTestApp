package com.example.vktestapplication.data

import com.google.gson.annotations.SerializedName


data class SearchRespond(
    var data:ArrayList<GifClass>
)


data class GifClass(
    val id:String?,
    val url:String?,
    val title:String?,
    val username:String?,
    var images: GifImages
)


data class GifImages(
    var original:OriginalImage
)

data class OriginalImage(
    var url: String? = null
)

