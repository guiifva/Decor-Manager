package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

interface ListProductsUseCase {
    fun execute(): List<ListProductsDto>
}