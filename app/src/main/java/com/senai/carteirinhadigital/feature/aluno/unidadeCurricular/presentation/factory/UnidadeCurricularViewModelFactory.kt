package com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.UnidadeCurricularViewModel

class UnidadeCurricularViewModelFactory(
    private val repository: com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.repository.UnidadeCurricularRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(_root_ide_package_.com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.UnidadeCurricularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return _root_ide_package_.com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.presentation.UnidadeCurricularViewModel(
                repository = repository
            ) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}