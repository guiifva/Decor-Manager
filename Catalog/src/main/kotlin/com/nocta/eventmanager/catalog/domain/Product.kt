package com.nocta.eventmanager.catalog.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Category cannot be empty")
    val name: String,

    @Column(nullable = false)
    @Min(value = 0, message = "Quantity must be greater than 0")
    var quantity: Int?,

    @Column(nullable = false)
    var reservedQuantity: Int = 0,

    @Column(nullable = false)
    val pricePerDay: Double = 0.0,

    @Column(nullable = true)
    val description: String? = null,

    @Column(nullable = false)
    var active: Boolean = true,

    @Min(value = 0, message = "Quantity must be greater than 0")
    var marketValue: Double?,

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    var categories: MutableList<Category>? = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "product_theme",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "theme_id")]
    )
    var themes: MutableList<Theme>? = mutableListOf()
){
    fun addCategories(categories: List<Category>){
        if (this.categories == null){
            this.categories = mutableListOf()
        }

        this.categories!!.addAll(categories)
    }

    fun addThemes(themes: List<Theme>){
        if (this.themes == null){
            this.themes = mutableListOf()
        }

        this.themes!!.addAll(themes)
    }

    fun isAvailable(quantity: Int? = null): Boolean {
        if (quantity == null){
            return this.quantity!! > this.reservedQuantity
        }

        if (quantity <= 0){
            throw Exception("Quantity must be greater than 0")
        }

        return quantity <= this.quantity!! - this.reservedQuantity
    }

    fun reserve(quantity: Int){
        if (quantity <= 0){
            throw Exception("Quantity must be greater than 0")
        }

        if (quantity > this.quantity!!){
            throw Exception("Quantity to reserve is greater than the available quantity")
        }

        this.reservedQuantity += quantity
    }
}