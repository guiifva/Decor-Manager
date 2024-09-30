package com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory

import com.nocta.eventmanager.catalog.infrastructure.mappers.toListProductsByThemeDto
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ListProductsByThemeUseCaseImpl(private val productRepository: ProductRepository) : ListProductsByThemeUseCase {
    override fun execute(themeId: UUID): List<ListProductsByThemeDto> {
        return productRepository.findByThemes_Id(themeId).map { it.toListProductsByThemeDto() }
    }
}