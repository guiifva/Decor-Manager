package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import java.util.*

data class GetProductDto(val id: UUID? = null,
                         val name: String,
                         val quantity: Int?,
                         val reservedQuantity: Int = 0,
                         val pricePerDay: Double = 0.0,
                         val description: String,
                         val active: Boolean = true,
                         val marketValue: Double?,
                         val categories: MutableList<GetProductDtoCategoryDto>? = null,
                         val themes: MutableList<GetProductThemeDto>? = null)


data class GetProductDtoCategoryDto(val id: UUID, val name: String, val description: String?)
data class GetProductThemeDto(val id: UUID, val name: String, val description: String?)
