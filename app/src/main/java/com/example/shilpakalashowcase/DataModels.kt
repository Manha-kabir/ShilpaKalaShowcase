package com.example.shilpakalashowcase

data class WipStage(
    val stageNumber: Int,
    val description: String,
    val imageUrl: String
)

data class Sculpture(
    val productId: String,
    val title: String,
    val material: String,
    val dimensions: String,
    val price: String,
    val imageUrl: String,
    val isWorkInProgress: Boolean = false,
    val wipStages: List<WipStage> = emptyList()
)

data class Artist(
    val id: String,
    val name: String,
    val location: String,
    val specialty: String,
    val bio: String,
    val profileImageUrl: String,
    val sculptures: List<Sculpture>
)