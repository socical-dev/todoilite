package com.socical.todolite.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/*
* 역할: 편집 화면의 UI 담당. (저장/뒤로는 콜백으로 위임)
* 현재는 로컬 상태(TextField)로만 동작
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
<<<<<<< HEAD
=======
    todoId: Long?,
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
    onDone: () -> Unit,
    vm: EditViewModel = viewModel()
) {

    val state by vm.uiState.collectAsState()
<<<<<<< HEAD
=======

    // 화면 진입 시 ui에 따라 상태 초기화 (추후에는 SavedStateHandle/Hilt 사용 예정)
    LaunchedEffect(todoId) { vm.load(todoId) }
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(if (vm.uiState.collectAsState().value.title.isBlank()) "Add Todo" else "Edit Todo")
            })
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)   //세로 방향으로 아이템 간격 설정
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = vm::onTitleChange,
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = vm::onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),    //EditText의 높이 제한
                minLines = 4
            )
            Button(
<<<<<<< HEAD
                onClick = { vm.save(onDone) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Save") }
=======
                onClick = onDone,   // 추후에 실제로 저장 후 popBackStack()으로 대체할 예정.
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Done")
            }
>>>>>>> a19b7de (feat(mvvm): add List/Edit ViewModels and UiState; bind screens to StateFlow)
        }
    }
}