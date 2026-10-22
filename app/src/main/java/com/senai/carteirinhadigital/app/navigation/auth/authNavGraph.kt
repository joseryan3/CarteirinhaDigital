package com.senai.carteirinhadigital.app.navigation.auth

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.senai.carteirinhadigital.app.navigation.GraphRoutes
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado
import com.senai.carteirinhadigital.feature.Login.domain.repository.LoginRepository
import com.senai.carteirinhadigital.feature.Login.presentation.LoginViewModel
import com.senai.carteirinhadigital.feature.Login.presentation.factory.LoginViewModelFactory
import com.senai.carteirinhadigital.feature.Login.presentation.screen.LoginScreen

fun NavGraphBuilder.authNavGraph(
    loginRepository: LoginRepository,
    onLoginSucesso: (UsuarioLogado) -> Unit
) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route = GraphRoutes.AUTH
    ) {
        composable(route = AuthRoutes.LOGIN) {
            val factory = remember(loginRepository) {
                LoginViewModelFactory(
                    repository = loginRepository
                )
            }

            val loginViewModel: LoginViewModel = viewModel(
                factory = factory
            )

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSucesso = onLoginSucesso
            )
        }
    }
}