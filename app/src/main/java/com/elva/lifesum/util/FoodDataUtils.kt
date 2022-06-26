package com.elva.lifesum.util

import java.util.*

object FoodDataUtils {
    const val FOOD_ID_MIN = 1
    const val FOOD_ID_MAX = 200

    /**
     * returns a foodItemId in range [1, 200]
     */
    fun generateFoodItemId () : Int = getRandomNumber(FOOD_ID_MIN, FOOD_ID_MAX)

    /**
     * returns a random int in range [min, max]
     */
    fun getRandomNumber(min : Int, max : Int) = Random().nextInt((max - min) + 1) + min
}