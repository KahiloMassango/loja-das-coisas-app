package com.example.store.feature.product_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ProductImageCarousel(
    modifier: Modifier = Modifier,
    images: List<Int>
) {
    val pagerState = rememberPagerState { images.size }
    Box(modifier = modifier) {
        HorizontalPager(
            modifier = modifier
                .fillMaxWidth()
                .height(420.dp),
            pageSize = PageSize.Fixed(300.dp),
            state = pagerState,
            pageSpacing = 16.dp
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
        ) {
            repeat(pagerState.pageCount) { index ->
                val color = if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary
                else Color.Transparent
                Box(
                    modifier = Modifier
                        .height(3.dp)
                        .weight(1f)
                        .background(color, RoundedCornerShape(50))
                )
            }
        }
    }
}