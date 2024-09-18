package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import java.util.*

interface GetProductUseCase {
    fun execute(id: UUID): GetProductDto
}