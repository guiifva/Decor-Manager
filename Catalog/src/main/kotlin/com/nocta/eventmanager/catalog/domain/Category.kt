package com.nocta.eventmanager.catalog.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = UUID.randomUUID(),

    @NotEmpty(message = "Category cannot be empty")
    @Column(nullable = false, unique = true)
    var name: String,

    @Size(min = 3, message = "Description must be greater than 3 characters")
    var description: String? = null,

    var active: Boolean = true,

    @ManyToMany(mappedBy = "categories")
    var products: MutableList<Product>? = mutableListOf()
) {
    fun changeDescription(description: String?) {
        if (description != null && description.length <= 3){
            throw IllegalArgumentException()
        }

        this.description = description
    }

    fun inactivate() {
        if (!active){
            return
        }

        this.active = false
    }
}