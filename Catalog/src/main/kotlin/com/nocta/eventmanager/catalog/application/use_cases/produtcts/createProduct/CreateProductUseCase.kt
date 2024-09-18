package com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct

interface CreateProductUseCase {
    fun execute(productDto: CreateProductDto): CreatedProductDto
}