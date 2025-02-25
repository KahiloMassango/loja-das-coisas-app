package com.example.store.core.network

import com.example.store.core.network.common.extractErrorMessage
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductWithVariationDtoRes
import com.example.store.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class ProductNetworkDatasourceImp(
    private val apiService: AppApiService
): ProductNetworkDatasource {

    override suspend fun getProductsByGenderAndCategory(
        gender: String?,
        category: String?
    ): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts(gender, category)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: Exception) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun getProductById(id: String): Result<ProductWithVariationDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductById(id)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: Exception) {
                Result.failure(Exception("Verifique sua conexão com a internet, ${e.message}"))
            }
        }
    }

    override suspend fun searchProducts(query: String): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchProducts(query)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: Exception) {
                Result.failure(Exception("Verifique sua conexão com a internet, ${e.message}"))
            }
        }
    }
}