package com.example.raithavarta.utils

import android.content.Context

class PreferenceManager(context: Context) {

    private val prefs =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveLanguage(language: String) {

        prefs.edit().putString("language", language).apply()
    }

    fun getLanguage(): String {

        return prefs.getString(
            "language",
            ""
        ) ?: ""
    }
}