package com.elva.lifesum.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodItemResponse(
    @field:Json(name = "meta") val meta: Meta,
    @field:Json(name = "response") val foodItem: FoodItem
)

data class Meta(
    @field:Json(name = "code") val code: Int
)
