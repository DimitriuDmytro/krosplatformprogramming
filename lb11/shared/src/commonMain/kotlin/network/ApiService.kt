package network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*

interface ApiService {
    suspend fun getData(): String
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getData(): String {
        // Заміни на реальний URL зі свого завдання
        return client.get("https://jsonplaceholder.typicode.com/posts/1").body()
    }
}

class Repository(private val apiService: ApiService) {
    suspend fun fetchData(): NetworkResult<String> {
        return try {
            NetworkResult.Success(apiService.getData())
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Помилка мережі")
        }
    }
}