package com.example.echovibe.routes

sealed class Routes(val route: String) {
    data object Registration : Routes("register")
    data object Login : Routes("login")
}