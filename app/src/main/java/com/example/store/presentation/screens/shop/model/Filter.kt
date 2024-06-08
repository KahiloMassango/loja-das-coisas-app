package com.example.store.presentation.screens.shop.model


data class Filter(val name: String, val description: String)


private val womenFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Skirt", "Saias"),
    Filter("Blouses","Blusas"),
    Filter("Dresses","Vestidos")
)

private val menFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Pants", "Calças"),
    Filter("Shorts","Calção")
)

private val kidsFilters = listOf(
    Filter("All", "Tudo"),
    Filter("T-shirt", "T-shirt"),
    Filter("Skirt", "Saia"),
    Filter("Blouses","Blusas")
)

val sectionFilters = mapOf(
    ShopSection.Women to womenFilters,
    ShopSection.Men to menFilters,
    ShopSection.Kids to kidsFilters
)

fun getCategorySectionFilters(section: ShopSection): List<Filter> = sectionFilters[section]!!
