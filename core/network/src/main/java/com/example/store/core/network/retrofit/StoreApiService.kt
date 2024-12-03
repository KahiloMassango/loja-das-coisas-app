package com.example.store.core.network.retrofit

import com.example.store.core.network.model.product.network_product.NetworkProduct
import com.example.store.core.network.model.product.network_product_With_Variation.NetworkProductWithVariation
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApiService {
    @GET("products/{category}/{subCategory}")
    suspend fun getProducts(
        @Path("category") category: String,
        @Path("subCategory") subCategory: String): List<NetworkProduct>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): NetworkProductWithVariation
}