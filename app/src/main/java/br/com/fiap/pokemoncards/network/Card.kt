package br.com.fiap.pokemoncards.network

import com.squareup.moshi.Json

data class Card (
    val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "supertype") val supertype: String = "N/A",
    @Json(name = "types") val types: List<String> = listOf(),
    @Json(name = "evolvesFrom") val evolvesFrom: String = "N/A",
    @Json(name = "evolvesTo") val evolvesTo: List<String> = listOf(),
    @Json(name = "level") val level: String = "N/A",
    @Json(name = "hp") val hp: String = "N/A",
    @Json(name = "images") val images: CardImages,
    @Json(name = "attacks") val attacks: List<CardAttacks> = listOf(),
    @Json(name = "rules") val rules: List<String> = listOf(),
    @Json(name = "rarity") val rarity: String = "N/A"
)