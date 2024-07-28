package com.example.store.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.store.core.ui.theme.StoreTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (state: Boolean) -> Unit
) {
    val width = 34.dp
    val height = 19.dp
    val swipeableState = rememberSwipeableState(
        initialValue = checked,
        confirmStateChange = { isChecked ->
            if (isChecked) {
                onCheckedChange(false)
            } else {
                onCheckedChange(true)
            }
            true
        }
    )

    val sizePx = with(LocalDensity.current) { (width - height).toPx() }
    val anchors = mapOf(0f to false, sizePx to true) // Maps anchor points (in px) to states

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(70.dp))
            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .size(height)
                .clip(RoundedCornerShape(50))
                .then(
                    if (!swipeableState.currentValue) {
                        Modifier.background(color = Color.White)
                    } else Modifier.background(color = MaterialTheme.colorScheme.scrim)
                )
                .clickable {
                    scope.launch {

                        if (!swipeableState.currentValue) {
                            swipeableState.animateTo(true)
                        } else {
                            swipeableState.animateTo(false)
                        }

                    }
                }
        )
    }
}

@ThemePreviews
@Composable
private fun Previeweview() {
    StoreTheme {
        CustomSwitch(
            checked = false,
            onCheckedChange = {}
        )
    }
}