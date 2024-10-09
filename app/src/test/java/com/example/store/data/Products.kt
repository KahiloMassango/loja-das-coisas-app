package com.example.store.data

import com.example.store.core.model.Product


val PRODUCT_1 = Product(
    id = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    name = "Vestido Floral",
    description = "Vestido floral com estampa vibrante",
    price = 49.99,
    section = "Women",
    category = "clothes",
    images = listOf("https://example.com/vestido1.jpg", "https://example.com/vestido2.jpg"),
    storeId = "1fa85f64-5717-4562-b3fc-2c963f66afa1",
    availableColors = listOf("Vermelho", "Verde"),
    availableSizes = listOf("S", "M", "L"),
    averageRating = 4,
    totalRating = 120,
    publishedAt = "15/10/2023"
)
val PRODUCT_2 = Product(
    id = "3fa85f64-5717-4562-b3fc-2c963f66afa7",
    name = "Tênis Esportivo",
    description = "Tênis esportivo confortável para corridas",
    price = 89.99,
    section = "Men",
    category = "shoes",
    images = listOf("https://example.com/tenis1.jpg", "https://example.com/tenis2.jpg"),
    storeId = "2fa85f64-5717-4562-b3fc-2c963f66afa2",
    availableColors = listOf("Azul", "Preto"),
    availableSizes = listOf("39", "40", "41", "42"),
    averageRating = 5,
    totalRating = 220,
    publishedAt = "6/04/2023"
)
val PRODUCT_3 = Product(
    id = "3fa85f64-5717-4562-b3fc-2c963f66afa8",
    name = "Bolsa de Couro",
    description = "Bolsa de couro elegante e espaçosa",
    price = 129.99,
    section = "Women",
    category = "accessory",
    images = listOf("https://example.com/bolsa1.jpg", "https://example.com/bolsa2.jpg"),
    storeId = "3fa85f64-5717-4562-b3fc-2c963f66afa3",
    availableColors = listOf("Marrom", "Preto"),
    availableSizes = listOf("Único"),
    averageRating = 4,
    totalRating = 75,
    publishedAt = "1/07/2023"
)