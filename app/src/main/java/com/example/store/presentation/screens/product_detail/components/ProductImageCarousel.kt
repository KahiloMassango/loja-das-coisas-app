package com.example.store.presentation.screens.product_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductImageCarousel(
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { images.size }
    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp),
        state = pagerState,
        pageSpacing = 4.dp
    ) { page ->
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = images[page]),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}