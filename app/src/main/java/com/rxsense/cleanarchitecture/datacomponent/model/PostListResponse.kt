package com.rxsense.cleanarchitecture.datacomponent.model


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PostListResponse(
    @Json(name = "data")
    val data: List<NetworkPost>?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "offset")
    val offset: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "total")
    val total: Int?
)