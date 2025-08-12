package com.socical.todolite.presentation.list

import androidx.lifecycle.ViewModel
import com.socical.todolite.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ListUiState(
    val items: List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
        val demo = (1L..5L).map { id ->
            Todo(id = id, title = "Todo #$id", description = "Desc $id")
        }
        _uiState.value = ListUiState(items = demo)
    }
}