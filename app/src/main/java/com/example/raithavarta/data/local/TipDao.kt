package com.example.raithavarta.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TipDao {

    @Query("SELECT * FROM tips")
    suspend fun getAllTips(): List<TipEntity>

    @Query("DELETE FROM tips")
    suspend fun deleteAllTips()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTips(tips: List<TipEntity>)
}