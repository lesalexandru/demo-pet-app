package com.example.demopetapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimalsDto(
    val animals: List<AnimalDto.Animal>,
    val pagination: Pagination
) {
    data class Pagination(
        @SerializedName("count_per_page")
        val countPerPage: Int,
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("_links")
        val links: Links,
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("total_pages")
        val totalPages: Int
    ) {
        data class Links(
            val next: Next,
            val previous: Previous
        ) {
            data class Next(
                val href: String
            )

            data class Previous(
                val href: String
            )
        }
    }
}
