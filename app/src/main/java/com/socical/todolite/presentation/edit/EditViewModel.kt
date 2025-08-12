package com.socical.todolite.presentation.edit

<<<<<<< HEAD
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socical.todolite.di.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
=======
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)

data class EditUiState(
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

<<<<<<< HEAD
class EditViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val repo = Graph.todoRepo
=======
class EditViewModel : ViewModel() {
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)

    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

<<<<<<< HEAD
    private val todoId: Long? = handle.get<Long>("id")

    init {
        // 수정 모드면 기존 값 로드
        if (todoId != null) {
            viewModelScope.launch {
                val item = repo.get(todoId)
                if (item != null) {
                    _uiState.value = EditUiState(
                        title = item.title,
                        description = item.description.orEmpty()
                    )
                }
            }
        }
    }

    fun onTitleChange(v: String) { _uiState.value = _uiState.value.copy(title = v) }
    fun onDescriptionChange(v: String) { _uiState.value = _uiState.value.copy(description = v) }

    fun save(onDone: () -> Unit) {
        val s = _uiState.value
        viewModelScope.launch {
            if (todoId == null) {
                repo.add(s.title, s.description)
            } else {
                repo.update(todoId, s.title, s.description)
            }
            onDone()
        }
=======
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
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
    }
}