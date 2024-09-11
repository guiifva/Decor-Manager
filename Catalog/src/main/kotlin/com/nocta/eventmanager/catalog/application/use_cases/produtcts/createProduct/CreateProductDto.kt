package com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct

import java.util.*

data class CreateProductDto(
    val name: String,
    val quantity: Int?,
    val description: String?,
    val marketValue: Double?,
    val pricePerDay: Double?,
    val categoryIds: List<UUID>?,
    val themeIds: List<UUID>?
)
