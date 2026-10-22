package com.senai.carteirinhadigital.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.senai.carteirinhadigital.app.di.AppContainer
import com.senai.carteirinhadigital.app.navigation.aluno.alunoNavGraph
import com.senai.carteirinhadigital.app.navigation.auth.authNavGraph
import com.senai.carteirinhadigital.app.navigation.professor.professorNavGraph
import com.senai.carteirinhadigital.app.session.SessionViewModel
import com.senai.carteirinhadigital.app.session.SessionViewModelFactory
import com.senai.carteirinhadigital.feature.Login.domain.model.PerfilAcesso

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    container: AppContainer
) {
    val sessionFactory = remember(container.sessionTokenStore) {
        SessionViewModelFactory(sessionTokenStore = container.sessionTokenStore)
    }

    // Instanciado no topo do AppNavHost para durar durante toda a Activity
    val sessionViewModel: SessionViewModel = viewModel(factory = sessionFactory)

    fun graphFor(perfil: PerfilAcesso): String {
        return when (perfil) {
            PerfilAcesso.ALUNO -> GraphRoutes.ALUNO
            PerfilAcesso.PROFESSOR -> GraphRoutes.PROFESSOR
        }
    }

    fun replaceGraph(currentGraph: String, destinationGraph: String) {
        if (navController.currentDestination?.parent?.route == destinationGraph ||
            navController.currentDestination?.route == destinationGraph) {
            return
        }
        navController.navigate(destinationGraph) {
            popUpTo(0) { inclusive = true } // Limpa a pilha com segurança
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = GraphRoutes.AUTH
    ) {
        authNavGraph(
            loginRepository = container.loginRepository,
            onLoginSucesso = { usuario ->
                sessionViewModel.setUsuarioLogado(usuario)
                replaceGraph(
                    currentGraph = GraphRoutes.AUTH,
                    destinationGraph = graphFor(usuario.perfil)
                )
            }
        )

        alunoNavGraph(
            navController = navController,
            usuarioLogadoFlow = sessionViewModel.usuarioLogado,
            unidadeCurricularRepository = container.unidadeCurricularRepository,
            darkTheme = darkTheme,
            onDarkThemeChange = onDarkThemeChange,
            onLogout = {
                sessionViewModel.limparSessao()
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onSessaoAusente = {
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onPerfilInvalido = { perfil ->
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = graphFor(perfil)
                )
            }
        )

        professorNavGraph(
            navController = navController,
            usuarioLogadoFlow = sessionViewModel.usuarioLogado,
            turmaRepository = container.professorTurmaRepository,
            unidadeCurricularRepository = container.professorUnidadeCurricularRepository,
            darkTheme = darkTheme,
            onDarkThemeChange = onDarkThemeChange,
            onLogout = {
                sessionViewModel.limparSessao()
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onSessaoAusente = {
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onPerfilInvalido = { perfil ->
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = graphFor(perfil)
                )
            }
        )
    }
}
