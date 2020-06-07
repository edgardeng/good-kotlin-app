package com.edgar.movie.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class MovieImage(
    val small: String,
    val large: String,
    val medium: String
)