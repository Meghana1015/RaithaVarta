package com.example.raithavarta.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LanguageScreen(
    onLanguageSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Select Language",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onLanguageSelected("en")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("English")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onLanguageSelected("kn")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kannada")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onLanguageSelected("hi")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hindi")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onLanguageSelected("te")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Telugu")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onLanguageSelected("ta")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tamil")
        }
    }
}