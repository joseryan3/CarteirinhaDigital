package com.senai.carteirinhadigital.feature.professor.turmas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TurmasViewModel(
    private val repository: ProfessorTurmaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TurmasUiState())
    val uiState: StateFlow<TurmasUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listarTurmas()
                .onSuccess { lista ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            listaTurmas = lista,
                            errorMessage = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Erro ao carregar turmas."
                        )
                    }
                }
        }
    }
}
