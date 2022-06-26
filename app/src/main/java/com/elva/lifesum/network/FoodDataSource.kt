package com.elva.lifesum.network

import com.elva.lifesum.model.FoodItem
import javax.inject.Inject

/**
 * Fetch food info from remote data source
 */
class FoodDataSource @Inject constructor(private val foodService : IFoodService) {

    suspend fun fetchFoodItemFromRemote(foodId : Int) : Resource<FoodItem> {
        val response = foodService.fetchFoodItem(foodId)
        val foodItem = response.body()?.foodItem
        return if (response.isSuccessful && foodItem != null) {
            Resource.Success<FoodItem>(foodItem.apply { id = foodId })
        } else {
            Resource.Failed<FoodItem>(response.message())
        }
    }


}