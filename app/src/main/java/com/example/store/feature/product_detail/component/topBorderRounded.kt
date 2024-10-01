package com.example.store.feature.product_detail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.topBorderRounded(
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
    strokeWidth: Dp = 2.dp,
    cornerRadiusDp: Dp = 25.dp
) = composed(factory = {
    val localDensity = LocalDensity.current
    val strokeWidthPx = localDensity.run { strokeWidth.toPx() }
    val cornerRadius = localDensity.run { cornerRadiusDp.toPx() }
    Modifier.drawWithContent {
        drawContent()
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidthPx
        )
        drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset.Zero,
            size = Size(cornerRadius * 2, cornerRadius * 2),
            style = Stroke(width = strokeWidthPx)
        )
        drawArc(
            color = color,
            startAngle = - 90f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(size.width - cornerRadius * 2, 0f),
            size = Size(cornerRadius * 2, cornerRadius * 2),
            style = Stroke(width = strokeWidthPx)
        )
    }
}
)