package com.example.store.feature.reviews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.feature.reviews.ReviewsScreen
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