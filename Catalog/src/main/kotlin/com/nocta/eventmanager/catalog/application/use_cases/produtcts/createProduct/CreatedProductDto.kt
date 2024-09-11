package com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct

import java.util.*

data class CreatedProductDto(val id: UUID? = null,
                             val name: String,
                             val quantity: Int?,
                             val reservedQuantity: Int?,
                             val pricePerDay: Double?,
                             val description: String?,
                             val active: Boolean?,
                             val marketValue: Double?,
                             val categories: MutableList<CreatedProductCategoryDto>? = null,
                             val themes: MutableList<CreatedProductThemeDto>? = null
)

