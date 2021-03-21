package dev.haenara.sample.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.http.parseQueryString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Thread.sleep
import kotlin.concurrent.thread

suspend fun main() {
    val cats = MyClass().getCatFromApi()
    println(cats.joinToString(separator = "\n") { it.toString() })
}

class MyClass {
    suspend fun getCatFromApi(): List<Cat> {
        val client = HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }

        return client.get<List<Cat>>("https://api.thecatapi.com/v1/images/search?limit=5")
    }

    companion object {
        private val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }
}
