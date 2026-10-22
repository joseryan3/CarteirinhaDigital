package com.senai.carteirinhadigital.app.di

import com.senai.carteirinhadigital.core.auth.SessionTokenStore
import com.senai.carteirinhadigital.core.network.NetworkClient
import com.senai.carteirinhadigital.feature.Login.data.remote.service.AuthApi
import com.senai.carteirinhadigital.feature.Login.data.repository.ApiLoginRepositoryImpl
import com.senai.carteirinhadigital.feature.Login.data.repository.FakeAuthRepository
import com.senai.carteirinhadigital.feature.Login.domain.repository.LoginRepository
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.data.remote.service.UnidadeCurricularApi
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.professor.turmas.data.remote.service.ProfessorTurmaApi
import com.senai.carteirinhadigital.feature.professor.turmas.data.repository.ApiProfessorTurmaRepositoryImpl
import com.senai.carteirinhadigital.feature.professor.turmas.domain.repository.ProfessorTurmaRepository
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.remote.service.ProfessorUnidadeCurricularApi
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.data.repository.ApiProfessorUnidadeCurricularRepositoryImpl
import com.senai.carteirinhadigital.feature.professor.unidadeCurricular.domain.repository.ProfessorUnidadeCurricularRepository

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore: SessionTokenStore = SessionTokenStore()
    private val networkClient = NetworkClient(
        baseUrl = BASE_URL,
        sessionTokenStore = sessionTokenStore
    )

    private val authApi: AuthApi by lazy {
        networkClient.createPublic(AuthApi::class.java)
    }

    private val unidadeCurricularApi: UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularApi::class.java)
    }

    private val professorTurmaApi: ProfessorTurmaApi by lazy {
        networkClient.createAuthenticated(ProfessorTurmaApi::class.java)
    }

    private val professorUnidadeCurricularApi: ProfessorUnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(ProfessorUnidadeCurricularApi::class.java)
    }

    override val loginRepository: LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY) {
            FakeAuthRepository()
        } else {
            ApiLoginRepositoryImpl(api = authApi)
        }
    }

    override val unidadeCurricularRepository: UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(api = unidadeCurricularApi)
    }

    override val professorTurmaRepository: ProfessorTurmaRepository by lazy {
        ApiProfessorTurmaRepositoryImpl(api = professorTurmaApi)
    }

    override val professorUnidadeCurricularRepository: ProfessorUnidadeCurricularRepository by lazy {
        ApiProfessorUnidadeCurricularRepositoryImpl(api = professorUnidadeCurricularApi)
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}
