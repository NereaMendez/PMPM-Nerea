package com.example.dogapicompose

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.dogapicompose.model.RazasRespuesta
import com.example.dogapicompose.model.FotosRespuesta

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getRazas(): Response<RazasRespuesta>

    @GET("breed/{raza}/images")
    suspend fun getImagenes(@Path("raza") raza: String): Response<FotosRespuesta>

    @GET("breed/{raza}/{subraza}/images")
    suspend fun getImagenesSub(@Path("raza") raza: String, @Path("subraza") subraza: String): Response<FotosRespuesta>
}