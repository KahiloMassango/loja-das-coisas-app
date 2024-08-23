package com.example.store.feature.help_center.contact_us.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SocialMediaButton(
    modifier: Modifier = Modifier,
    icon: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clickable(interactionSource = null, indication = null) { onClick() }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
