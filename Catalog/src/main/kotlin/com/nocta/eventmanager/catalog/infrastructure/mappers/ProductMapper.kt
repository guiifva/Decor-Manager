package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductDto
import com.nocta.eventmanager.catalog.domain.Category
import com.nocta.eventmanager.catalog.domain.Product
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ProductMapper {
    companion object {
        val INSTANCE: ProductMapper = Mappers.getMapper(ProductMapper::class.java)
    }

    fun toCreatedDTO(product: Product): CreatedProductDto
    fun toGetProductDto(product: Product): GetProductDto
    fun toEntity(productDTO: CreateProductDto): Product
    fun toCreatedProductCategoryDto(category: Category): CreatedProductCategoryDto
}