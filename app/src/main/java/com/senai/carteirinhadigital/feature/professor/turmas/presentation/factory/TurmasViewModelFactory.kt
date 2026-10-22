package com.senai.carteirinhadigital.feature.professor.turmas.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import com.senai.carteirinhadigital.feature.professor.turmas.presentation.TurmasViewModel

class TurmasViewModelFactory(
    private val repository: ProfessorTurmaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TurmasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TurmasViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
