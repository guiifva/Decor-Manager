package com.nocta.eventmanager.catalog.application.use_cases.produtcts.reserveProducts

import com.nocta.eventmanager.catalog.domain.exceptions.DuplicateProductException
import com.nocta.eventmanager.catalog.domain.exceptions.ProductNotAvailableException
import com.nocta.eventmanager.catalog.domain.exceptions.SomeProductsNotFound
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ReserveProductsUseCaseImpl(private val productRepository: ProductRepository) : ReserveProductsUseCase {
    override fun execute(productsToReserve: List<ProductToReserveDto>) {

        if (productsToReserve.size != productsToReserve.distinctBy { it.productId }.size){
            throw DuplicateProductException()
        }

        val products = productRepository.findAllById(productsToReserve.map { it.productId })

        if (products.size != productsToReserve.size){
            throw SomeProductsNotFound()
        }

        products.forEach { product ->
            productsToReserve.find { it.productId == product.id }?.let { dto ->
                if (!product.isAvailable(dto.quantity)){
                    throw ProductNotAvailableException()
                }

                product.reserve(dto.quantity)
            }
        }

        productRepository.saveAll(products)
    }
}