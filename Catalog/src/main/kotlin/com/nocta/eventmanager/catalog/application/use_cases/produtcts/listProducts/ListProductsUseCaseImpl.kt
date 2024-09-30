package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import com.nocta.eventmanager.catalog.infrastructure.mappers.toListProductsDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ListProductsUseCaseImpl(private val repository: ProductRepository) : ListProductsUseCase {
    override fun execute(): List<ListProductsDto> {
        return repository.findAll().map { it.toListProductsDto() }
    }
}