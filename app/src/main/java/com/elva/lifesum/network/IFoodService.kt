package com.elva.lifesum.network

import com.elva.lifesum.model.FoodItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IFoodService {

    @GET("codetest")
    suspend fun fetchFoodItem(
        @Query("foodid") foodId: Int
    ): Response<FoodItemResponse>
}