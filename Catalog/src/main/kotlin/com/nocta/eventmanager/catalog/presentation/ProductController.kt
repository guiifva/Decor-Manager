package com.nocta.eventmanager.catalog.presentation

import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreateProductUseCase
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.createProduct.CreatedProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.GetProductUseCase
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.ListProductsDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.getProduct.ListProductsUseCase
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.productIsAvailable.ProductIsAvailableUseCase
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.reserveProducts.ProductToReserveDto
import com.nocta.eventmanager.catalog.application.use_cases.produtcts.reserveProducts.ReserveProductsUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/products", produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "Products", description = "Product management")
class ProductController(private val createProductUseCase: CreateProductUseCase,
                        private val getProductUseCase: GetProductUseCase,
                        private val listProductsUseCase: ListProductsUseCase,
                        private val productIsAvailableUseCase: ProductIsAvailableUseCase,
                        private val reserveProductsUseCase: ReserveProductsUseCase
) {

    @GetMapping
    fun listProducts(): ResponseEntity<List<ListProductsDto>> {
        return ResponseEntity.ok(listProductsUseCase.execute())
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID): ResponseEntity<GetProductDto> {
        return ResponseEntity.ok(getProductUseCase.execute(id))
    }

    @PostMapping
    fun createProduct(@RequestBody productDto: CreateProductDto,uriBuilder: UriComponentsBuilder)
    : ResponseEntity<CreatedProductDto> {
        val createdProduct = createProductUseCase.execute(productDto)
        val uri = uriBuilder.path("/products/${createdProduct.id}").build().toUri()

        return ResponseEntity.created(uri).body(createdProduct)
    }

    @GetMapping("{id}/is-available")
    fun isProductAvailable(@PathVariable id: UUID): ResponseEntity<Any> {
        return ResponseEntity.ok(object { val isAvailable = productIsAvailableUseCase.execute(id)})
    }

    @GetMapping("{id}/is-available/{quantity}")
    fun isProductAvailableWithQuantity(@PathVariable id: UUID, @PathVariable quantity: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(object { val isAvailable = productIsAvailableUseCase.execute(id, quantity)})
    }

    @PatchMapping("/reserve")
    fun reserveProduct(@RequestBody productsToReserveDto: List<ProductToReserveDto>): ResponseEntity<Any> {
        reserveProductsUseCase.execute(productsToReserveDto)
        return ResponseEntity.noContent().build()
    }
}