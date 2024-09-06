package com.nocta.eventmanager.catalog.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

@Entity(name = "categories")
data class Category (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    @NotEmpty(message = "Category cannot be empty")
    val name : String,

    @Min(value = 3, message = "Description must be greater than 3 characters")
    var description : String?,

    var active : Boolean = true,

    @OneToMany(mappedBy = "category")
    var items : List<Item> = ArrayList(),
){
    fun changeDescription(description : String?){
        if (description != null && description.length <= 3)
            throw IllegalArgumentException()

        this.description = description
    }
}