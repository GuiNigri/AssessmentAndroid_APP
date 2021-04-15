package br.pro.nigri.assessmentandroid.api

import br.pro.nigri.assessmentandroid.api.service.NumVerifyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl("http://apilayer.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNumVerifyService():NumVerifyService {
        return retrofit.create(NumVerifyService::class.java)
    }
}