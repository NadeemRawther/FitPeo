package com.nads.fitpeo.data.datasource

import com.nads.fitpeo.model.FitPeoResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface FitPeoDataSource:DataSource {
    @GET("photos")
    override suspend fun getList():List<FitPeoResponseItem>

    @GET("photos/{id}")
    override suspend fun getListItem(@Path("id")id:String):FitPeoResponseItem
}