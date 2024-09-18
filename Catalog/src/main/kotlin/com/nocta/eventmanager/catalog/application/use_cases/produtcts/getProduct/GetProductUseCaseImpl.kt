package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import com.nocta.eventmanager.catalog.domain.exceptions.ProductNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toGetProductDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetProductUseCaseImpl(private val repository: ProductRepository) : GetProductUseCase {

    override fun execute(id: UUID): GetProductDto {
        val product = repository.findById(id).orElseThrow { ProductNotFoundException() }

        return product.toGetProductDto()
    }
}