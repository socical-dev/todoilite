package com.socical.todolite.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.socical.todolite.presentation.edit.EditScreen
import com.socical.todolite.presentation.list.ListScreen

/*
* NavHost 를 지도 라고 생각하면 길찾기 역할을 하는 NavController 도 함께 선언해줘야 한다.
* 지금은 "임시 화면"으로 이동만 검증한다.
* (추후에 실제 ListScreen/EditScreen으로 교체할 예정이다.)
* */
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable(route = "list") {
            ListScreen(
                onAddClick = { navController.navigate("edit") },
                onItemClick = { id -> navController.navigate("edit/$id") }
            )
        }
        composable(route = "edit") {
            EditScreen(
                todoId = null,
                onDone = { navController.popBackStack() }
            )
        }
        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            EditScreen(
                todoId = id,
                onDone = { navController.popBackStack() }
            )
        }

// 더미 코드
//            // 중앙 정렬된 임시 화면 + 이동 테스트 버튼 2개
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("List (placeholder)")
//                Button(onClick = { navController.navigate("edit") }) {
//                    Text("Go to Add (edit)")
//                }
//                Button(onClick = { navController.navigate("edit/42") }) {
//                    Text("Go to Edit #42 (edit/{id})")
//                }
//            }
//        }

//        // 2) 새로 추가 화면 (id 없음)
//        composable(route = "edit") {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("Edit (add mode) - id: null")
//                Button(onClick = { navController.popBackStack() }) {
//                    Text("Back")
//                }
//            }
//        }
//
//        // 3) 기존 항목 수정 화면 (id 경로 변수)
//        composable(
//            route = "edit/{id}", arguments = listOf(navArgument("id") { type = NavType.LongType })
//        ) { backStackEntry ->
//            val id = backStackEntry.arguments?.getLong("id")
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("Edit (update mode) - id: $id")
//                Button(onClick = { navController.popBackStack() }) {
//                    Text("Back")
//                }
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavGraphPreview() {
    AppNavGraph()
}