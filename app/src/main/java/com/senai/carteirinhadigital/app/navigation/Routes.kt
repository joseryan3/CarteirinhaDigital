package com.senai.carteirinhadigital.app.navigation

import kotlinx.serialization.Serializable

sealed class Routes (val route: String){


    data object Login : Routes("login")

    data object Carteirinha : Routes("carteirinha")

    data object HomeAluno : Routes("homeAluno")

    data object UCAluno : Routes("ucAluno")

}