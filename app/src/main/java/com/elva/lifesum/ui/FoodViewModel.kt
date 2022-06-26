package com.elva.lifesum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elva.lifesum.model.FoodItem
import com.elva.lifesum.model.State
import com.elva.lifesum.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val foodRepository : FoodRepository) : ViewModel(){
    private val _foodItemFlow : MutableStateFlow<State<FoodItem>> = MutableStateFlow(State.loading())
    val foodItemFlow : MutableStateFlow<State<FoodItem>> = _foodItemFlow

    fun getFoodItem(foodId : Int) {
        _foodItemFlow.value = State.loading()
        viewModelScope.launch {
            foodRepository.fetchFoodItem(foodId)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _foodItemFlow.value = state }
        }
    }
}