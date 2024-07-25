package com.example.store.core.data.mock

import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo

import java.util.UUID

val ratingsList = listOf(
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 4,
        comment = "Ótimo produto!",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 5,
        comment = "Excelente qualidade!",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa7",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 4,
        comment = "Muito bom!",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa8",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 3,
        comment = "Bom, mas poderia ser melhor.",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa9",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 5,
        comment = "Perfeito!",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afaa",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 4,
        comment = "Muito útil.",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afab",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 3,
        comment = "Bom custo-benefício.",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afac",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 5,
        comment = "Recomendo!",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afad",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 4,
        comment = "Muito bom produto.",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afae",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = UUID.randomUUID().toString(),
        rate = 4,
        comment = "Gostei bastante.",
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afaf",
        userId = UUID.randomUUID().toString(),
        ratingDate = "2024-07-23"
    ),
    Rating(
        id = "1",
        rate = 5,
        comment = "Produto de ótima qualidade e chegou muito rápido.",
        productId = "1",
        userId = "user1",
        ratingDate = "2023-02-10"
    ),
    Rating(
        id = "2",
        rate = 4,
        comment = "Muito confortável e estiloso.",
        productId = "1",
        userId = "user2",
        ratingDate = "2023-02-12"
    ),
    Rating(
        id = "3",
        rate = 5,
        comment = null,
        productId = "2",
        userId = "user3",
        ratingDate = "2023-02-15"
    ),
    Rating(
        id = "4",
        rate = 4,
        comment = "Jeans de boa qualidade, mas a modelagem é um pouco justa.",
        productId = "2",
        userId = "user4",
        ratingDate = "2023-02-17"
    ),
    Rating(
        id = "5",
        rate = 5,
        comment = "Tênis muito confortável e leve.",
        productId = "3",
        userId = "user5",
        ratingDate = "2023-02-20"
    ),
    Rating(
        id = "6",
        rate = 4,
        comment = "Relógio bonito e elegante, mas a pulseira é um pouco apertada.",
        productId = "4",
        userId = "user6",
        ratingDate = "2023-02-22"
    ),
    Rating(
        id = "7",
        rate = 5,
        comment = "Vestido lindo e veste muito bem.",
        productId = "5",
        userId = "user7",
        ratingDate = "2023-02-24"
    ),
    Rating(
        id = "8",
        rate = 4,
        comment = "Camisa de boa qualidade, mas o tecido amassa facilmente.",
        productId = "6",
        userId = "user8",
        ratingDate = "2023-02-26"
    ),
    Rating(
        id = "9",
        rate = 5,
        comment = "Sandália confortável e estilosa.",
        productId = "7",
        userId = "user9",
        ratingDate = "2023-03-01"
    ),
    Rating(
        id = "10",
        rate = 4,
        comment = "Mochila resistente e espaçosa, mas as alças são um pouco finas.",
        productId = "8",
        userId = "user10",
        ratingDate = "2023-03-03"
    ),
    Rating(
        id = "11",
        rate = 5,
        comment = null,
        productId = "9",
        userId = "user11",
        ratingDate = "2023-03-05"
    ),
    Rating(
        id = "14",
        rate = 4,
        comment = "Casaco de couro legítimo e muito bonito, mas o tamanho é um pouco grande.",
        productId = "10",
        userId = "user12",
        ratingDate = "2023-03-07"
    ),
    Rating(
        id = "13",
        rate = 5,
        comment = "Saia linda e confortável.",
        productId = "11",
        userId = "user13",
        ratingDate = "2023-03-09"
    ),
    Rating(
        id = "4",
        rate = 4,
        comment = "Tênis bonito, mas o solado é um pouco fino.",
        productId = "11",
        userId = "user14",
        ratingDate = "2023-03-11"
    ),
    Rating(
        id = "5",
        rate = 5,
        comment = "Relógio muito bonito e resistente.",
        productId = "10",
        userId = "user15",
        ratingDate = "2023-03-13"
    ),
    Rating(
        id = "16",
        rate = 4,
        comment = "Camiseta confortável, mas o tecido é um pouco transparente.",
        productId = "4",
        userId = "user16",
        ratingDate = "2023-03-15"
    ),
    Rating(
        id = "17",
        rate = 5,
        comment = "Calça jeans de boa qualidade e veste muito bem.",
        productId = "5",
        userId = "user17",
        ratingDate = "2023-03-17"
    ),
    Rating(
        id = "18",
        rate = 4,
        comment = "Tênis estiloso, mas o cadarço é um pouco curto.",
        productId = "6",
        userId = "user18",
        ratingDate = "2023-03-19"
    ),
    Rating(
        id = "19",
        rate = 5,
        comment = "Relógio muito bonito e elegante.",
        productId = "7",
        userId = "user19",
        ratingDate = "2023-03-21"
    ),
    Rating(
        id = "20",
        rate = 4,
        comment = "Camisa de boa qualidade, mas o tecido amassa facilmente.",
        productId = "6",
        userId = "user20",
        ratingDate = "2023-03-23"
    )
)

val ratingInfosList = listOf(
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        totalRatings = 120,
        averageRating = 4,
        listOf(60, 40, 15, 3, 2)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa7",
        totalRatings = 220,
        averageRating = 5,
        listOf(180, 30, 8, 2, 0)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa8",
        totalRatings = 75,
        averageRating = 4,
        listOf(30, 30, 10, 4, 1)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afa9",
        totalRatings = 90,
        averageRating = 3,
        listOf(25, 25, 20, 5, 0)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afaa",
        totalRatings = 150,
        averageRating = 5,
        listOf(120, 20, 5, 3, 2)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afab",
        totalRatings = 200,
        averageRating = 4,
        listOf(80, 40, 10, 2, 0)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afac",
        totalRatings = 50,
        averageRating = 3,
        listOf(10, 6, 15, 4, 1)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afad",
        totalRatings = 180,
        averageRating = 5,
        listOf(150, 20, 5, 2, 0)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afae",
        totalRatings = 110,
        averageRating = 4,
        listOf(50, 10, 3, 30, 1)
    ),
    RatingInfo(
        id = UUID.randomUUID().toString(),
        productId = "3fa85f64-5717-4562-b3fc-2c963f66afaf",
        totalRatings = 130,
        averageRating = 4,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "1",
        productId = "1",
        totalRatings = 10,
        averageRating = 4,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "2",
        productId = "2",
        totalRatings = 8,
        averageRating = 3,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "3",
        productId = "3",
        totalRatings = 12,
        averageRating = 2,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "4",
        productId = "4",
        totalRatings = 6,
        averageRating = 1,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "5",
        productId = "5",
        totalRatings = 10,
        averageRating = 5,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "6",
        productId = "6",
        totalRatings = 8,
        averageRating = 4,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "7",
        productId = "7",
        totalRatings = 12,
        averageRating = 4,
        listOf(60, 0, 10, 2, 30)
    ),
    RatingInfo(
        id = "8",
        productId = "8",
        totalRatings = 6,
        averageRating = 4,
        listOf(60, 0, 10, 2, 30)
    )
)
