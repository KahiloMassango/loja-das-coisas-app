package com.example.store.feature.help_center

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.help_center.contact_us.ContactUsContent
import com.example.store.feature.help_center.faq.FaqContent
import com.example.store.feature.help_center.faq.components.HelpCenterTabRow
import com.example.store.feature.help_center.faq.components.QuestionCategory

@Composable
fun HelpCenterScreen(
    modifier: Modifier = Modifier,
    onNavigationUp: () -> Unit = {}
) {
    var currentTab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Centro de ajuda",
                elevation = 4.dp,
                canNavigateBack = true,
                onNavigateUp = onNavigationUp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            HelpCenterTabRow(
                modifier = Modifier,
                selectedTab = currentTab,
                onTabClick = { currentTab = it }
            )
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedContent(
                targetState = currentTab, label = ""
            ) { tab ->
                when (tab) {
                    0 -> FaqContent()
                    else -> ContactUsContent()
                }
            }
        }
    }
}

@Composable
fun QuestionFilter(
    modifier: Modifier = Modifier,
    currentCategory: QuestionCategory,
    onCategoryClick: (QuestionCategory) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        QuestionCategory.entries.forEach { category ->
            val selected = currentCategory == category

            item {
                val backgroundColor = if (selected) MaterialTheme.colorScheme.primaryContainer else
                    MaterialTheme.colorScheme.secondary
                Box(
                    modifier = Modifier
                        .shadow(elevation = 4.dp, RoundedCornerShape(50))
                        .clip(RoundedCornerShape(50))
                        .background(backgroundColor, RoundedCornerShape(50))

                        .clickable { onCategoryClick(category) }
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = category.description,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                            else MaterialTheme.colorScheme.onSecondary,
                    )
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        HelpCenterScreen()
    }
}

