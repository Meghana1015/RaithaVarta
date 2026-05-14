package com.example.raithavarta.ui.screens

import androidx.compose.ui.res.stringResource
import com.example.raithavarta.R
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raithavarta.ui.components.SuccessStoryCard
import com.example.raithavarta.ui.components.TipCard
import com.example.raithavarta.viewmodel.TipViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.height
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    viewModel: TipViewModel
) {

    val tips = viewModel.tips

    var selectedCrop by remember {
        mutableStateOf("All")
    }

    var query by remember {
        mutableStateOf("")
    }

    var answer by remember {
        mutableStateOf("")
    }

    val crops = listOf(
        "All",
        "Paddy",
        "Tomato",
        "Coconut",
        "Areca Nut",
        "Ragi"
    )

    val filteredTips = tips.filter {

        selectedCrop == "All" ||
                it.cropType == selectedCrop
    }

    Column(

        modifier = Modifier.fillMaxSize()

    ) {

        Row(

            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(8.dp)

        ) {

            crops.forEach { crop ->

                Button(

                    onClick = {
                        selectedCrop = crop
                    },

                    modifier = Modifier.padding(4.dp)

                ) {

                    Text(text = crop)
                }
            }
        }

        val pagerState = rememberPagerState()

        HorizontalPager(

            count = filteredTips.size,

            state = pagerState,

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) { page ->

            TipCard(
                tip = filteredTips[page]
            )
        }

        SuccessStoryCard()

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = stringResource(R.string.home_title),
            fontSize = 22.sp,
            modifier = Modifier.padding(12.dp)
        )

        OutlinedTextField(

            value = query,

            onValueChange = {
                query = it
            },

            label = {
                Text("Enter crop issue")
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        Button(

            onClick = {

                answer = when {

                    query.contains("yellow", true) ->
                        "Possible nitrogen deficiency. Add compost or urea."

                    query.contains("pest", true) ->
                        "Spray neem oil during evening hours."

                    query.contains("dry", true) ->
                        "Increase irrigation and add mulch."

                    query.contains("fungus", true) ->
                        "Apply organic fungicide every 10 days."

                    query.isNotEmpty() ->
                        "Consult nearby Krishi Vigyana Kendra."

                    else ->
                        "Please enter crop issue."
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        ) {

            Text("Ask Expert")
        }

        if (answer.isNotEmpty()) {

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)

            ) {

                Column(

                    modifier = Modifier.padding(16.dp)

                ) {

                    Text(
                        text = "🌱 Expert Solution",
                        fontSize = 20.sp
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = answer,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}