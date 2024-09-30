package com.nocta.eventmanager.catalog.application.use_cases.produtcts.productIsAvailable

import com.nocta.eventmanager.catalog.domain.exceptions.ProductNotFoundException
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductIsAvailableUseCaseImpl(private val productRepository: ProductRepository) : ProductIsAvailableUseCase {
    override fun execute(productId: UUID, quantity: Int?): Boolean {
        val product = productRepository.findById(productId).orElseThrow{ ProductNotFoundException() }

        return product.isAvailable(quantity)
    }
}