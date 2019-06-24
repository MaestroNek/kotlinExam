package com.example.krutik_exam

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    var path: String,
    @SerializedName("extension")
    var extension: String
)

data class Price(
    @SerializedName("price")
    var price: Double
)

data class ComicsResult(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail,
    @SerializedName("description")
    var description: String,
    @SerializedName("prices")
    var prices: ArrayList<Price>
)

data class ComicsData(
    @SerializedName("results")
    var results: ArrayList<ComicsResult>
)

data class ComicsResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: ComicsData
)

