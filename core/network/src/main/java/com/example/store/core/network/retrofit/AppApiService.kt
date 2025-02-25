package com.example.store.core.network.retrofit

import com.example.store.core.network.model.Response
import com.example.store.core.network.model.StoreDtoRes
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductWithVariationDtoRes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiService {

    @GET("products/search")
    suspend fun searchProducts(@Query("query") gender: String): Response<List<ProductDtoRes>>

    @GET("products/filter")
    suspend fun getProducts(
        @Query("gender") gender: String?,
        @Query("category") category: String?
    ): Response<List<ProductDtoRes>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductWithVariationDtoRes>

    @GET("store/detail/{id}")
    suspend fun getStoreDetail(@Path("id") id: String): Response<StoreDtoRes>

}