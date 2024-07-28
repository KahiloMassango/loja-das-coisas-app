package com.example.store.core.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
annotation class ThemePreviews

@Preview(name = "Landscape Mode", showBackground = true, device = Devices.TABLET, widthDp = 640)
@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PIXEL_6)
annotation class OrientationPreviews

@Preview(name = "Default Font Size", fontScale = 1f)
@Preview(name = "Large Font Size", fontScale = 1.5f)
annotation class FontScalePreviews