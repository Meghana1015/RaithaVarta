package com.example.raithavarta.data.repository

import com.example.raithavarta.data.local.TipDao
import com.example.raithavarta.data.local.TipEntity

class TipRepository(
    private val dao: TipDao
) {

    suspend fun insertTips(
        tips: List<TipEntity>
    ) {

        dao.insertTips(tips)
    }

    suspend fun getAllTips(): List<TipEntity> {

        return dao.getAllTips()
    }

    suspend fun deleteAllTips() {

        dao.deleteAllTips()
    }
}