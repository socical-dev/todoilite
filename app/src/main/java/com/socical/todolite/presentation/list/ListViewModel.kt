package com.socical.todolite.presentation.list

import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import androidx.lifecycle.viewModelScope
import com.socical.todolite.di.Graph
=======
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
import com.socical.todolite.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
<<<<<<< HEAD
import kotlinx.coroutines.launch

data class ListUiState(
    val items: List<Todo> = emptyList(), val isLoading: Boolean = false, val error: String? = null
=======

data class ListUiState(
    val items: List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
)

class ListViewModel : ViewModel() {

<<<<<<< HEAD
    private val repo = Graph.todoRepo

=======
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
<<<<<<< HEAD
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
=======
        val demo = (1L..5L).map { id ->
            Todo(id = id, title = "Todo #$id", description = "Desc $id")
        }
        _uiState.value = ListUiState(items = demo)
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
    }
}