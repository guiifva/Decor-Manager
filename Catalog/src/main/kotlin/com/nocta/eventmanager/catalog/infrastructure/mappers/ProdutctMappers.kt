package com.nocta.eventmanager.catalog.infrastructure.mappers

import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductDtoCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.ListProductsDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory.ListProductsByCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory.ListProductsByThemeDto
import com.nocta.eventmanager.catalog.domain.Category
import com.nocta.eventmanager.catalog.domain.Product
import com.nocta.eventmanager.catalog.domain.Theme
import java.util.*

fun Product.toCreatedProductDto(): CreatedProductDto {
    return CreatedProductDto(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        reservedQuantity = this.reservedQuantity,
        pricePerDay = this.pricePerDay,
        description = this.description,
        active = this.active,
        marketValue = this.marketValue,
        categories = this.categories?.map { it.toCreatedProductCategoryDto() }?.toMutableList(),
        themes = this.themes?.map { it.toCreatedProductThemeDto() }?.toMutableList()
    )
}

fun Category.toCreatedProductCategoryDto(): CreatedProductCategoryDto {
    return CreatedProductCategoryDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun Theme.toCreatedProductThemeDto(): CreatedProductThemeDto {
    return CreatedProductThemeDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun CreateProductDto.toProduct(): Product {
    return Product(
        name = this.name,
        quantity = this.quantity,
        description = this.description,
        marketValue = this.marketValue,
        pricePerDay = this.pricePerDay ?: 0.0,
        categories = null,
        themes = null
    )
}

fun Product.toGetProductDto(): GetProductDto {
    return GetProductDto(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        reservedQuantity = this.reservedQuantity,
        pricePerDay = this.pricePerDay,
        description = this.description ?: "",
        active = this.active,
        marketValue = this.marketValue,
        categories = this.categories?.map { it.toGetProductDtoCategoryDto() }?.toMutableList(),
        themes = this.themes?.map { it.toGetProductThemeDto() }?.toMutableList()
    )
}

fun Category.toGetProductDtoCategoryDto(): GetProductDtoCategoryDto {
    return GetProductDtoCategoryDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun Theme.toGetProductThemeDto(): GetProductThemeDto {
    return GetProductThemeDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
}

fun Product.toListProductsDto(): ListProductsDto {
    return ListProductsDto(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        reservedQuantity = this.reservedQuantity,
        pricePerDay = this.pricePerDay,
        description = this.description ?: "",
        active = this.active,
        marketValue = this.marketValue
    )
}

fun Product.toListProductsByCategoryDto(): ListProductsByCategoryDto {
    return ListProductsByCategoryDto(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        reservedQuantity = this.reservedQuantity,
        pricePerDay = this.pricePerDay,
        description = this.description ?: "",
        active = this.active,
        marketValue = this.marketValue
    )
}

fun Product.toListProductsByThemeDto(): ListProductsByThemeDto {
    return ListProductsByThemeDto(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        reservedQuantity = this.reservedQuantity,
        pricePerDay = this.pricePerDay,
        description = this.description ?: "",
        active = this.active,
        marketValue = this.marketValue
    )
}



