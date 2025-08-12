package com.socical.todolite.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/*
* 역할: 목록 화면의 UI 담당. (네비게이션은 콜백으로 위임)
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onAddClick: () -> Unit,
    onItemClick: (Long) -> Unit,
    vm: ListViewModel = viewModel()
) {
    val state by vm.uiState.collectAsState()

    /*
    * 화면 틀(상단 바, 하단 바, 콘텐츠 영역 등)을 구성해주는 역할
    * 이 틀 안에 들어갈 콘텐츠가 다른 요소에 겹치지 않도록 inner라는 이름의 여백 값을 제공
    * */
    Scaffold(
        topBar = { TopAppBar(title = { Text("Todo List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) { Text("+") }
        }
    ) { inner ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner),    // Scaffold가 알려준 여백(inner)만큼 LazyColumn의 안쪽 여백 설정
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.items) { todo ->
                ElevatedCard(
                    onClick = { onItemClick(todo.id) },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                        if (!todo.description.isNullOrBlank()) {
                            Spacer(Modifier.height(4.dp))
                            Text(todo.description)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen(onAddClick = {}, onItemClick = {})
}