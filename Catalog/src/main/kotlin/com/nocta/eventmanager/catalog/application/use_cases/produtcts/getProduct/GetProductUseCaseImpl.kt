package com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct

import com.nocta.eventmanager.catalog.domain.exceptions.ProductNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.mappers.ProductMapper
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetProductUseCaseImpl(private val repository: ProductRepository) : GetProductUseCase {

    private val productMapper = ProductMapper.INSTANCE

    override fun getProduct(id: UUID): GetProductDto {
        val product = repository.findById(id).orElseThrow { ProductNotFoundException() }

        return productMapper.toGetProductDto(product)
    }
}