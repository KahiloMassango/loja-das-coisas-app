package com.example.store.feature.shop.model


data class Filter(val name: String, val description: String)

val womenFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Skirt", "Saias"),
    Filter("Blouses","Blusas"),
    Filter("Dresses","Vestidos")
)

val menFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Pants", "Calças"),
    Filter("Shorts","Calção")
)

val kidsFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Skirt", "Saia"),
    Filter("Blouses","Blusas")
)


