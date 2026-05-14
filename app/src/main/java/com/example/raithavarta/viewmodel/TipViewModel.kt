package com.example.raithavarta.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.raithavarta.data.local.TipEntity
import com.example.raithavarta.data.repository.TipRepository

class TipViewModel(
    private val repository: TipRepository
) : ViewModel() {

    var tips = mutableStateListOf<TipEntity>()

    suspend fun loadTips() {

        tips.clear()

        tips.addAll(
            repository.getAllTips()
        )
    }
}