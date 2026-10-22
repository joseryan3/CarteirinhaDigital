package com.senai.carteirinhadigital.app.navigation.professor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.senai.carteirinhadigital.app.navigation.GraphRoutes
import com.senai.carteirinhadigital.app.navigation.ProfileGuard
import com.senai.carteirinhadigital.app.ui.AppShell
import com.senai.carteirinhadigital.feature.Login.domain.model.PerfilAcesso
import com.senai.carteirinhadigital.feature.Login.domain.model.UsuarioLogado
import com.senai.carteirinhadigital.feature.professor.home.presentation.screen.HomeProfessorScreen
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import com.senai.carteirinhadigital.feature.professor.turmas.presentation.TurmasViewModel
import com.senai.carteirinhadigital.feature.professor.turmas.presentation.factory.TurmasViewModelFactory
import com.senai.carteirinhadigital.feature.professor.turmas.presentation.screen.TurmasScreen
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation.UnidadesCurricularesProfessorViewModel
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation.factory.UnidadesCurricularesProfessorViewModelFactory
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation.screen.UnidadesCurricularesProfessorScreen
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.professorNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    turmaRepository: ProfessorTurmaRepository,
    unidadeCurricularRepository: ProfessorUnidadeCurricularRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(startDestination = ProfessorRoutes.HOME, route = GraphRoutes.PROFESSOR) {
        composable(route = ProfessorRoutes.HOME) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {},
                    onLogout = onLogout
                ) { innerPadding ->
                    HomeProfessorScreen(
                        usuarioLogado = usuario,
                        onTurmasClick = {
                            navController.navigate(ProfessorRoutes.TURMAS)
                        },
                        onUnidadesCurricularesClick = {
                            navController.navigate(ProfessorRoutes.UNIDADES_CURRICULARES)
                        },
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.TURMAS) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(turmaRepository) {
                    TurmasViewModelFactory(repository = turmaRepository)
                }
                val viewModel: TurmasViewModel = viewModel(factory = factory)

                AppShell(
                    title = "Minhas Turmas",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = onLogout
                ) { innerPadding ->
                    TurmasScreen(
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.UNIDADES_CURRICULARES) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(unidadeCurricularRepository) {
                    UnidadesCurricularesProfessorViewModelFactory(repository = unidadeCurricularRepository)
                }
                val viewModel: UnidadesCurricularesProfessorViewModel = viewModel(factory = factory)

                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = onLogout
                ) { innerPadding ->
                    UnidadesCurricularesProfessorScreen(
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}
