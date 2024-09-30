package com.nocta.eventmanager.catalog.application.use_cases.produtcts.productIsAvailable

import java.util.*

interface ProductIsAvailableUseCase {
    fun execute(productId: UUID, quantity: Int? = null): Boolean
}