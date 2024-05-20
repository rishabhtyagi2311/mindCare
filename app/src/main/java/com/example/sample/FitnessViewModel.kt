package com.example.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FitnessViewModel : ViewModel() {
    private val repository = FitnessRepository()

    private val _categoriesState = MutableStateFlow<List<catgeoryExercises>>(emptyList())
    val categoriesState: StateFlow<List<catgeoryExercises>> = _categoriesState

    private val _exercisesState = MutableStateFlow<List<exercises>>(emptyList())
    val exercisesState: StateFlow<List<exercises>> = _exercisesState

    fun fetchCategories() {
        viewModelScope.launch {
            val categories = repository.getCategories()
            _categoriesState.value = categories
        }
    }

    fun fetchExercises(categoryId: String) {
        viewModelScope.launch {
            val exercises = repository.getExercisesByCategory(categoryId)
            _exercisesState.value = exercises
        }
    }
}