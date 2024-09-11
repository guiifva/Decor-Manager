package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import com.nocta.eventmanager.catalog.domain.Category
import java.util.*

data class GetProductDto(val id: UUID? = null,
                         val name: String,
                         val quantity: Int?,
                         val reservedQuantity: Int = 0,
                         val pricePerDay: Double = 0.0,
                         val description: String,
                         val active: Boolean = true,
                         val marketValue: Double?,
                         val categories: MutableList<Category>? = mutableListOf())
