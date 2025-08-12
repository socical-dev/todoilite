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
* */
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.List.route
    ) {
        composable(Route.List.route) {
            ListScreen(
                onAddClick = { navController.navigate(Route.Edit.route) },
                onItemClick = { id -> navController.navigate(Route.EditWithId.path(id)) }
            )
        }
        composable(Route.Edit.route) {
            EditScreen(onDone = { navController.popBackStack() })
        }
        composable(
            route = Route.EditWithId.route,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            EditScreen(onDone = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavGraphPreview() {
    AppNavGraph()
}