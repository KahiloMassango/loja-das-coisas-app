package com.example.store.core.ui.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.hotFlow(scope: CoroutineScope, initialValue: T): StateFlow<T> {
    return this.stateIn(
        scope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue
    )
}