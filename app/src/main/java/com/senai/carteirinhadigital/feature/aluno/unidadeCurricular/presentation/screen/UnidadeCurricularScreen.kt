package com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.UnidadeCurricularViewModel
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.component.UnidadeCurricularCard


@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier,
    viewModel: com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.UnidadeCurricularViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = uiState.errorMessage

    LaunchedEffect(Unit) {
        viewModel.carregar()
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = {
                        viewModel.carregar()
                    }
                ) {
                    Text("Tentar novamente")
                }
            }
        }

        uiState.listaUnidadesCurriculares.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma unidade curricular encontrada.")
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                verticalArrangement = Arrangement
                    .spacedBy(12.dp)
            ) {
                items(
                    items = uiState.listaUnidadesCurriculares,
                    key = { it.id }
                ) { unidadeCurricular ->
                    _root_ide_package_.com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.component.UnidadeCurricularCard(
                        unidadeCurricular = unidadeCurricular
                    )
                }
            }
        }
    }
}