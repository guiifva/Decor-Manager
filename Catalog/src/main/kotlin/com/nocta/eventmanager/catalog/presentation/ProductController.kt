package com.nocta.eventmanager.catalog.presentation

import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductUseCase
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductDto
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/products", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(private val createProductUseCase: CreateProductUseCase) {

    @PostMapping
    fun createProduct(
        @RequestBody productDto: CreateProductDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CreatedProductDto> {
        val createdProduct = createProductUseCase.execute(productDto)
        val uri = uriBuilder.path("/products/${createdProduct.id}").build().toUri()

        return ResponseEntity.created(uri).body(createdProduct)
    }
}