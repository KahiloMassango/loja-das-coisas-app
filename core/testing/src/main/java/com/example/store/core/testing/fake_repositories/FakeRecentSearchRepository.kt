package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.model.search.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRecentSearchRepository: RecentSearchRepository {

    private val recentSearches = mutableListOf<RecentSearch>()


    override suspend fun saveSearch(query: String) {
        recentSearches.add(RecentSearch(recentSearches.size+1, query))
    }

    override fun getRecentSearches(): Flow<List<RecentSearch>> {
        return flowOf(recentSearches)
    }

    override suspend fun deleteRecentSearchById(id: Int) {
        recentSearches.removeIf { it.id == id }
    }

    override suspend fun clearAllRecentSearches() {
        recentSearches.clear()
    }
}