package com.example.demopetapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimalDto(val animal: Animal) {
    data class Animal(
        val age: String,
        val attributes: Attributes,
        val breeds: Breeds,
        val coat: String,
        val colors: Colors,
        val contact: Contact,
        val description: String,
        val distance: Double?,
        val environment: Environment,
        val gender: String,
        val id: Long,
        @SerializedName("_links")
        val links: Links,
        val name: String,
        @SerializedName("organization_id")
        val organizationId: String,
        val photos: List<Photo>,
        @SerializedName("published_at")
        val publishedAt: String,
        val size: String,
        val species: String,
        val status: String,
        val tags: List<String>,
        val type: String,
        val url: String,
        val videos: List<Video>
    ) {
        data class Attributes(
            val declawed: Boolean,
            @SerializedName("house_trained")
            val houseTrained: Boolean,
            @SerializedName("shots_current")
            val shotsCurrent: Boolean,
            @SerializedName("spayed_neutered")
            val spayedNeutered: Boolean,
            @SerializedName("special_needs")
            val specialNeeds: Boolean
        )

        data class Breeds(
            val mixed: Boolean,
            val primary: String,
            val secondary: Any?,
            val unknown: Boolean
        )

        data class Colors(
            val primary: String,
            val secondary: Any?,
            val tertiary: Any?
        )

        data class Contact(
            val address: Address,
            val email: String,
            val phone: String
        ) {
            data class Address(
                val address1: Any?,
                val address2: Any?,
                val city: String,
                val country: String,
                val postcode: String,
                val state: String
            )
        }

        data class Environment(
            val cats: Boolean,
            val children: Boolean,
            val dogs: Boolean
        )

        data class Links(
            val organization: Organization,
            val self: Self,
            val type: Type
        ) {
            data class Organization(val href: String)

            data class Self(val href: String)

            data class Type(val href: String)
        }

        data class Photo(
            val full: String,
            val large: String,
            val medium: String,
            val small: String
        )

        data class Video(val embed: String)
    }
}
