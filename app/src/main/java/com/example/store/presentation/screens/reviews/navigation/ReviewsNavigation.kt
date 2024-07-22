package com.example.store.presentation.screens.reviews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.presentation.screens.reviews.ReviewsScreen
import kotlinx.serialization.Serializable


@Serializable
data class ReviewsRoute(val productId: String)

fun NavController.navigateToReviews(productId: String) = navigate(ReviewsRoute(productId))

fun NavGraphBuilder.reviewsScreen(
    onNavigateUp: () -> Unit
) {
    composable<ReviewsRoute> {
        ReviewsScreen(
            onNavigateUp = onNavigateUp
        )
    }
}