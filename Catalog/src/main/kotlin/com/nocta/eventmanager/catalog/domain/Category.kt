package com.nocta.eventmanager.catalog.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "categories")
data class Category(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    val id: UUID? = UUID.randomUUID(),

    @NotEmpty(message = "Category cannot be empty")
    val name: String,

    @Size(min = 3, message = "Description must be greater than 3 characters")
    var description: String?,

    var active: Boolean = true,

    @ManyToMany(mappedBy = "categories")
    val products: MutableList<Product>? = mutableListOf()
) {
    fun changeDescription(description: String?) {
        if (description != null && description.length <= 3)
            throw IllegalArgumentException()

        this.description = description
    }
}