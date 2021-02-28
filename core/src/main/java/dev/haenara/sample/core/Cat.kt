package dev.haenara.sample.core

import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
