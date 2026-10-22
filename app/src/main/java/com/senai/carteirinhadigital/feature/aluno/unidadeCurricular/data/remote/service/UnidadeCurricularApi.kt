package com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.data.remote.service

import com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.data.remote.dto.UnidadeCurricularDTO
import retrofit2.http.GET

interface UnidadeCurricularApi {

    @GET("unidades-curriculares")
    suspend fun listarUnidadesCurriculares():
            List<com.senai.carteirinhadigital.feature.aluno.unidadeCurricular.data.remote.dto.UnidadeCurricularDTO>
}