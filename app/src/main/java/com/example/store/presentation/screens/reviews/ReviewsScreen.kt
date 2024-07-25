package com.example.store.presentation.screens.reviews

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.common.LoadingScreen
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo
import com.example.store.presentation.component.StoreCenteredTopBar
import com.example.store.presentation.screens.reviews.components.AddCommentFab
import com.example.store.presentation.screens.reviews.components.AddCommentSheet
import com.example.store.presentation.screens.reviews.components.RatingStats
import com.example.store.presentation.screens.reviews.components.ReviewCard
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
    viewModel: ReviewsViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when(uiState) {
        is ReviewsUiState.Loading -> LoadingScreen(modifier)
        is ReviewsUiState.Error -> LoadingScreen(modifier)
        is ReviewsUiState.Success -> ReviewsContent(
            modifier = modifier,
            ratings = uiState.ratings,
            ratingInfo = uiState.ratingInfo,
            onNavigateUp = onNavigateUp,
            onAddReview = { rate, comment ->
                viewModel.addReview(rate, comment)
            }
        )

    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReviewsContent(
    modifier: Modifier = Modifier,
    ratings: List<Rating>,
    ratingInfo: RatingInfo,
    onAddReview: (rate: Int, comment: String?) -> Unit,
    onNavigateUp: () -> Unit
) {

    var rate by rememberSaveable { mutableIntStateOf(0) }
    var comment by rememberSaveable { mutableStateOf("") }
    var isCommentSheetOpen by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        floatingActionButton ={
            AddCommentFab(
                modifier = Modifier.shadow(5.dp, RoundedCornerShape(50)),
                onClick = { isCommentSheetOpen = true }
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

        if (isCommentSheetOpen){
            AddCommentSheet(
                modifier = Modifier,
                state = sheetState,
                rate = rate,
                comment = comment,
                onRateChange = { rate = it },
                onCommentChange = { comment = it },
                onDismissRequest =  { isCommentSheetOpen = false },
                onSend = {
                    onAddReview(rate, comment)
                    coroutineScope.launch {
                        delay(200)
                        sheetState.hide()
                    }.invokeOnCompletion { isCommentSheetOpen = false }
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
                        ratingInfo = ratingInfo
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "${ratings.size} Comentários",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                items(ratings) { rating ->
                    ReviewCard(
                        modifier = Modifier.padding(horizontal = 18.dp),
                        rating = rating
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
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