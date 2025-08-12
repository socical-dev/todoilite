package com.socical.todolite.presentation.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EditUiState(
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class EditViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    fun load(todoId: Long?) {
        _uiState.value = if (todoId == null) {
            EditUiState()
        } else {
            EditUiState(title = "Todo #$todoId", description = "Loaded from demo")
        }
    }

    fun onTitleChange(newValue: String) {
        _uiState.value = _uiState.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        _uiState.value = _uiState.value.copy(description = newValue)
    }
}