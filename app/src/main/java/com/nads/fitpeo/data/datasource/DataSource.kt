package com.nads.fitpeo.data.datasource

import com.nads.fitpeo.model.FitPeoResponseItem

interface DataSource {


    suspend fun getList():List<FitPeoResponseItem>

    suspend fun getListItem(id:String): FitPeoResponseItem



}