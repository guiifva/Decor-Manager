package com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct

import com.nocta.eventmanager.catalog.domain.exceptions.DomainException
import com.nocta.eventmanager.catalog.domain.exceptions.ProductAlreadyExistsException
import com.nocta.eventmanager.catalog.infrastructure.mappers.toCreatedProductDto
import com.nocta.eventmanager.catalog.infrastructure.mappers.toProduct
import com.nocta.eventmanager.catalog.infrastructure.repositories.CategoryRepository
import com.nocta.eventmanager.catalog.infrastructure.repositories.ProductRepository
import com.nocta.eventmanager.catalog.infrastructure.repositories.ThemeRepository
import org.springframework.stereotype.Service

@Service
class CreateProductUseCaseImpl(private val repository: ProductRepository,
                               private val categoryRepository: CategoryRepository,
                               private val themeRepository: ThemeRepository
) : CreateProductUseCase {

    override fun execute(productDto: CreateProductDto): CreatedProductDto {
        repository.findByName(productDto.name)?.let {
            throw ProductAlreadyExistsException("Product with name ${productDto.name} already exists")
        }

        val categories = categoryRepository.findByIdIn(productDto.categoryIds)

        if (productDto.categoryIds != null && (categories.isEmpty() || categories.size != productDto.categoryIds.size)) {
            throw DomainException("Categories not found")
        }

        val themes = themeRepository.findByIdIn(productDto.themeIds)

        if (productDto.themeIds != null && (themes.isEmpty() || themes.size != productDto.themeIds.size)) {
            throw DomainException("Categories not found")
        }

        val product = productDto.toProduct(categories, themes)

        product.addCategories(categories)
        product.addThemes(themes)

        repository.save(product)

        return product.toCreatedProductDto()
    }
}