package com.socical.todolite.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socical.todolite.di.Graph
import com.socical.todolite.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ListUiState(
    val items: List<Todo> = emptyList(), val isLoading: Boolean = false, val error: String? = null
)

class ListViewModel : ViewModel() {

    private val repo = Graph.todoRepo

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repo.todos.collect { list -> _uiState.value = ListUiState(items = list) }
        }
        // 데모 초기 데이터 (원하면 유지/삭제)
        viewModelScope.launch {
            if (_uiState.value.items.isEmpty()) {
                repo.add("Todo #1", "Desc 1")
                repo.add("Todo #2", "Desc 2")
            }
        }
    }
}