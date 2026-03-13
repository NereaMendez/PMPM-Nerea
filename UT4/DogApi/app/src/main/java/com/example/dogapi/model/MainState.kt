package com.example.dogapi.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class MainState {

    private val retrofitAPI = RetrofitAPI()

    suspend fun recuperaFotos(raza : String): DogRespuesta = withContext(Dispatchers.IO){

            val respuesta = retrofitAPI.retrofitService.getFotosPerros(raza)

       /* if (respuesta.isSuccessful) {
            DogRespuesta(respuesta.body()!!.status, respuesta.body()!!.message)
        } else {
            DogRespuesta("error", null)
        }*/

        if (respuesta.isSuccessful) {
            respuesta.body()!!
        } else {
            DogRespuesta("error", null)
        }

}}