package com.socical.todolite.app

sealed class Route(val route: String) {
    object List: Route("list")
    object Edit: Route("edit")  // 새로 추가
    object EditWithId : Route("edit/{id}") {    // 기존 항목 수정
        fun path(id: Long) = "edit/$id"
    }
}