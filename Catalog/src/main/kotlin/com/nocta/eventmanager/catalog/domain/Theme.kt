package com.nocta.eventmanager.catalog.domain

import com.nocta.eventmanager.catalog.domain.enums.Size
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "theme")
data class Theme(
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    val id: UUID? = null,

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Category cannot be empty")
    val name: String,

    @Enumerated(EnumType.STRING)
    val size: Size,

    val description: String,

    @Column(nullable = false)
    var active: Boolean = true,

    @Min(value = 0, message = "Price must be greater than 0")
    var price: Double? = null,

    @ManyToMany(mappedBy = "themes")
    val products: MutableList<Product>? = mutableListOf()
)