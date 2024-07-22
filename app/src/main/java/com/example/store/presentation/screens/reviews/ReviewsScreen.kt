package com.example.store.presentation.screens.reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.screens.reviews.components.AddCommentSheet
import com.example.store.presentation.screens.reviews.components.RatingStats
import com.example.store.presentation.screens.reviews.components.ReviewCard
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit
) {
    val ratings = listOf(12, 5, 4, 2, 0)
    var rate by rememberSaveable { mutableIntStateOf(0) }
    var comment by rememberSaveable { mutableStateOf("") }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        floatingActionButton ={
            AddCommentFab(
                modifier = Modifier.shadow(5.dp, RoundedCornerShape(50)),
                onClick = { isSheetOpen = true }
            )
        },
        topBar = {
            StoreCenteredTopBar(
                title = "Avaliações",
                onNavigateUp = onNavigateUp,
                elevation = 3.dp
            )
        }
    ) { paddingValues ->

        if (isSheetOpen){
            AddCommentSheet(
                modifier = Modifier,
                state = sheetState,
                rate = rate,
                comment = comment,
                onRateChange = { rate = it },
                onCommentChange = { comment = it },
                onDismissRequest =  { isSheetOpen = false },
                onSend = {
                    coroutineScope.launch {
                        delay(200)
                        sheetState.hide()
                    }.invokeOnCompletion { isSheetOpen = false }
                }
            )
        }
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Spacer(modifier = Modifier.height(26.dp))
                    RatingStats(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        totalRatings = 23,
                        ratings = ratings
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "8 Comentários",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                items(10) {
                    ReviewCard(modifier = Modifier.padding(horizontal = 18.dp))
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
private fun AddCommentFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(50)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Comentar",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}




@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        ReviewsScreen(
            onNavigateUp = {}
        )
    }
}