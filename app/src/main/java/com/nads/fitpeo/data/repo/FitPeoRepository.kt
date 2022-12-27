package com.nads.fitpeo.data.repo

import com.nads.fitpeo.model.FitPeoResponseItem

interface FitPeoRepository {
   suspend fun getFitPeoList(): Result<List<FitPeoResponseItem>>

   suspend fun getFitPeoListItem(id:String): Result<FitPeoResponseItem>
}