package com.nocta.eventmanager.catalog.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

@Entity(name = "items")
data class Item (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    @NotEmpty(message = "Category cannot be empty")
    val name : String,

    @Min(value = 0, message = "Quantity must be greater than 0")
    var quantity : Int?,

    @Min(value = 0, message = "Quantity must be greater than 0")
    var marketValue: Double?,

    @ManyToOne
    var category: Category
)