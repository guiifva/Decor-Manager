package com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory

import java.util.*

data class ListProductsByThemeDto(val id: UUID? = null,
                                     val name: String,
                                     val quantity: Int?,
                                     val reservedQuantity: Int = 0,
                                     val pricePerDay: Double = 0.0,
                                     val description: String,
                                     val active: Boolean = true,
                                     val marketValue: Double?)
