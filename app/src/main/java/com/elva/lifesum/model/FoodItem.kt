package com.elva.lifesum.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodItem(
    var id : Int = 0,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "calories") val calories: Int,
    @field:Json(name = "carbs") val carbs: Float,
    @field:Json(name = "protein") val protein: Float,
    @field:Json(name = "fat") val fat: Float,
    @field:Json(name = "saturatedfat") val saturatedfat: Float,
    @field:Json(name = "unsaturatedfat") val unsaturatedfat: Float,
    @field:Json(name = "fiber") val fiber: Float,
    @field:Json(name = "cholesterol") val cholesterol: Float,
    @field:Json(name = "sugar") val sugar: Float,
    @field:Json(name = "sodium") val sodium: Float,
    @field:Json(name = "potassium") val potassium: Float,
    @field:Json(name = "gramsperserving") val gramsperserving: Float,
    @field:Json(name = "pcstext") val pcstext: String
) {
    override fun toString(): String {
        return "FoodItem(id=$id, title='$title', calories=$calories, carbs=$carbs, protein=$protein, fat=$fat, saturatedfat=$saturatedfat, unsaturatedfat=$unsaturatedfat, fiber=$fiber, cholesterol=$cholesterol, sugar=$sugar, sodium=$sodium, potassium=$potassium, gramsperserving=$gramsperserving, pcstext='$pcstext')"
    }
}
