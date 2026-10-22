package com.senai.carteirinhadigital.app.di

import com.senai.carteirinhadigital.core.auth.SessionTokenStore
import com.senai.carteirinhadigital.feature.Login.domain.repository.LoginRepository
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
    val professorTurmaRepository: ProfessorTurmaRepository
    val professorUnidadeCurricularRepository: ProfessorUnidadeCurricularRepository
}
