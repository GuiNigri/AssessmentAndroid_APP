package br.pro.nigri.assessmentandroid.api.service

import br.pro.nigri.assessmentandroid.ViewModel.NumVerifyViewModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NumVerifyService {

    @GET("/api/validate")
    fun getNumVerify(@Query("access_key") key:String,@Query("number") numero:String,@Query("format") number:String): Call<NumVerifyViewModel>
}