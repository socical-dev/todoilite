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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
* 역할: 편집 화면의 UI 담당. (저장/뒤로는 콜백으로 위임)
* 현재는 로컬 상태(TextField)로만 동작
* 추후에 ViewModel의 상태로 교체 예정
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    todoId: Long?,
    onDone: () -> Unit
) {
    // 임시 입력 상태 (ViewModel 도입 전이기에 rememberSaveable 사용)
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(if (todoId == null) "Add Todo" else "Edit $todoId")
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
                value = title,
                onValueChange = { title = it },
                label = { Text("Title (placeholder)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description (placeholder)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),    //EditText의 높이 제한
                minLines = 4
            )
            Button(
                onClick = onDone,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Done (Demo)")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreen(todoId = null, onDone = {})
}