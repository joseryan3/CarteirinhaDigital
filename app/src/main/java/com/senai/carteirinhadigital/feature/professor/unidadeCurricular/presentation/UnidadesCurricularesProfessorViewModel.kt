package com.senai.carteirinhadigital.feature.professor.unidadeCurricular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnidadesCurricularesProfessorViewModel(
    private val repository: ProfessorUnidadeCurricularRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UnidadesCurricularesProfessorUiState())
    val uiState: StateFlow<UnidadesCurricularesProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listarUnidadesCurriculares()
                .onSuccess { lista ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            listaUCs = lista,
                            errorMessage = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Erro ao carregar unidades curriculares."
                        )
                    }
                }
        }
    }
}
