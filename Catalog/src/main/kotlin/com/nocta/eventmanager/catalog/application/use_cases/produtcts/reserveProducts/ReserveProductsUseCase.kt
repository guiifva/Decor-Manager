package com.nocta.eventmanager.catalog.application.use_cases.produtcts.reserveProducts

interface ReserveProductsUseCase {
    fun execute(productsToReserve: List<ProductToReserveDto>)
}