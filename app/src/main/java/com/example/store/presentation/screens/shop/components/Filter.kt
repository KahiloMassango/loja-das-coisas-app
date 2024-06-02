package com.example.store.presentation.screens.shop.components


internal data class Filter(val name: String, val description: String)


internal val filterList = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Skirt", "Saia"),
    Filter("Blouses","Blusas")
)