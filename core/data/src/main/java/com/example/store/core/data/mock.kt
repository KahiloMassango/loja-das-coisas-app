package com.example.store.core.data

import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductItem
import com.example.store.core.model.product.ProductWithVariation
private val imagem = "https://i5.walmartimages.com/seo/Men-s-Nike-Air-Force-1-07-LV8-Certified-Fresh-Enamel-Green-Sail-DO9801-300-13_9a941cd3-636b-492c-b6c2-a6fe8f5e4ab5.129250e9ef95d08542e5587257020764.jpeg"
val P1 = Product(
    id  = "c3a40e62-0bee-4129-a2b7-d4da701c2ccb",
    description = "Originally designed for performance hoops, Nike Air cushioning delivers lasting comfort. Rubber outsole with heritage hoops pivot circles adds traction and ...",
    image = "https://i5.walmartimages.com/seo/Men-s-Nike-Air-Force-1-07-LV8-Certified-Fresh-Enamel-Green-Sail-DO9801-300-13_9a941cd3-636b-492c-b6c2-a6fe8f5e4ab5.129250e9ef95d08542e5587257020764.jpeg",
    storeId = "c3a40e62-0bee-4129-a2b7-d4da701c2ccm",
    storeName = "Nike",
    title = "Nike Air Force 1 '07 LV8"
)


private val productItems = listOf(
    ProductItem(
        id = "c3a40e62-0bee-4129-a2b7-d4da701c2cb",
        color = "green",
        image = imagem,
        price = 3000.0,
        size = "41",
        stockQuantity = 10
    ),
    ProductItem(
        id = "c3a40e62-0bee-4129-a2b7-d4da02ccb",
        color = "green",
        image = imagem,
        price = 5500.0,
        size = "45",
        stockQuantity = 9
    ),
    ProductItem(
        id = "c340e62-0bee-4129-a2b7-d4da701c2ccb",
        color = "green",
        image = imagem,
        price = 2000.0,
        size = "44",
        stockQuantity = 4
    ),
    ProductItem(
        id = "c3a40e62-0bee-4129-a2b7-d4da701c2ccb",
        color = "yellow",
        image = imagem,
        price = 2700.0,
        size = "39",
        stockQuantity = 15
    ),
    ProductItem(
        id = "c3a40e62-0bee-4129-a2b7-d4da70c2ccb",
        color = "yellow",
        image = imagem,
        price = 4850.0,
        size = "40",
        stockQuantity = 7
    )
)

val P1V = ProductWithVariation(
    id = "c3a40e62-0bee-4129-a2b7-d4da701c2ccf",
    description = "Originally designed for performance hoops, Nike Air cushioning delivers lasting comfort. Rubber outsole with heritage hoops pivot circles adds traction and ...",
    image = "https://i5.walmartimages.com/seo/Men-s-Nike-Air-Force-1-07-LV8-Certified-Fresh-Enamel-Green-Sail-DO9801-300-13_9a941cd3-636b-492c-b6c2-a6fe8f5e4ab5.129250e9ef95d08542e5587257020764.jpeg",
    storeId = "c3a40e62-0bee-4129-a2b7-d4da701c2ccm",
    storeName = "Nike",
    title = "Nike Air Force 1 '07 LV8",
    subCategory = "shoes",
    productItems = productItems
)