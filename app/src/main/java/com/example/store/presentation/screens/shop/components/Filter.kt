package com.example.store.presentation.screens.shop.components


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
    CategorySection.Women to womenFilters,
    CategorySection.Men to menFilters,
    CategorySection.Kids to kidsFilters
)

fun getCategorySectionFilters(section: CategorySection): List<Filter> = sectionFilters[section]!!
