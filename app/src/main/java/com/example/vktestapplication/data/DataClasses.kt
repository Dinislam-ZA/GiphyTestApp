package com.example.vktestapplication.data

import com.google.gson.annotations.SerializedName


data class SearchRespond(
    var data:ArrayList<GifClass>
)


data class GifClass(
    var id:String? = null,
    var url:String? = null,
    var title:String? = null,
    var username:String? = null,
    var images: GifImages
)

data class GifImages(
    var original:OriginalImage
)

data class OriginalImage(
    var url: String? = null
)

