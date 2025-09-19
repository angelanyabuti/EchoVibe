package com.example.echovibe.routes

sealed class Routes(val route: String) {
    data object Registration : Routes("register")
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object Search : Routes("search")
    data object Profile : Routes("profile")
    data object TodayMood : Routes("todayMood")
    data object NowPlaying : Routes("nowPlaying/{trackName}") {
        fun createRoute(trackName: String) = "nowPlaying/$trackName"
    }
}