package com.nocta.eventmanager.catalog.application.use_cases.produtcts.reserveProducts

import java.util.*

data class ProductToReserveDto (
    val productId: UUID,
    val quantity: Int
)
