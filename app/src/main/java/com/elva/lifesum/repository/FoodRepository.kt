package com.elva.lifesum.repository

import com.elva.lifesum.model.FoodItem
import com.elva.lifesum.network.FoodDataSource
import com.elva.lifesum.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository to load food item data from remote or local db
 */
class FoodRepository @Inject constructor(private val foodDataSource: FoodDataSource) {

    fun fetchFoodItem(foodId : Int) : Flow<Resource<FoodItem>> =
        flow<Resource<FoodItem>> {
            val resource = foodDataSource.fetchFoodItemFromRemote(foodId)
            emit(resource)
        }.catch { e->
            e.printStackTrace()
            emit(Resource.Failed("Network error! Can't get latest food item."))
        }

}