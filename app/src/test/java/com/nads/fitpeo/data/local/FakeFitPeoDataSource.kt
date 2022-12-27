package com.nads.fitpeo.data.local

import com.nads.fitpeo.data.datasource.DataSource
import com.nads.fitpeo.model.FitPeoResponseItem

class FakeFitPeoDataSource(var fitpeolist: MutableList<FitPeoResponseItem>):DataSource {

    override suspend fun getList(): List<FitPeoResponseItem> {
                 return fitpeolist
    }

    override suspend fun getListItem(id: String): FitPeoResponseItem {
        return fitpeolist[id.toInt()]

    }
}