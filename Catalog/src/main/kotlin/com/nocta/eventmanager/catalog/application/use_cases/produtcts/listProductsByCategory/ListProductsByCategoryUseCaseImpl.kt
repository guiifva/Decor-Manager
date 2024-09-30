package com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory

import com.nocta.eventmanager.catalog.infrastructure.mappers.toListProductsByCategoryDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ListProductsByCategoryUseCaseImpl(private val productRepository: ProductRepository) : ListProductsByCategoryUseCase {
    override fun execute(categoryId: UUID): List<ListProductsByCategoryDto> {
        return productRepository.findByCategories_Id(categoryId).map { it.toListProductsByCategoryDto() }
    }
}