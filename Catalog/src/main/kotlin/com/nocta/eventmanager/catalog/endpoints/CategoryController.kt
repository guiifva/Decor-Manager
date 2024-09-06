package com.nocta.eventmanager.catalog.endpoints

import com.nocta.eventmanager.catalog.application.dtos.CreateCategoryDto
import com.nocta.eventmanager.catalog.application.dtos.GetCategoryDto
import com.nocta.eventmanager.catalog.application.dtos.ListCategoriesDto
import com.nocta.eventmanager.catalog.application.use_cases.CreateCategoryUseCase
import com.nocta.eventmanager.catalog.application.use_cases.ListCategoriesUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val listCategoriesUseCase: ListCategoriesUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase
) {

    @Operation(summary = "Add a new category", description = "Add a new category")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "Category added successfully",
                content = [Content(
                    schema = Schema(implementation = GetCategoryDto::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad request",
                content = [Content(
                    schema = Schema(implementation = ErrorResponse::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = [Content(
                    schema = Schema(implementation = ErrorResponse::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            )
        ]
    )
    @PostMapping
    fun insertCategory(@RequestBody createCategoryDto: CreateCategoryDto, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<GetCategoryDto> {
        val createdCategoryDto = createCategoryUseCase.createCategory(createCategoryDto)
        val uri = uriBuilder.path("/categories/${createdCategoryDto.id}").build().toUri()

        return ResponseEntity.created(uri).body(createdCategoryDto)
    }


    @GetMapping
    @Operation(summary = "List categories", description = "A list of categories")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Category list",
                content = [Content(
                    array = ArraySchema(schema = Schema(implementation = ListCategoriesDto::class)),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = [Content(
                    schema = Schema(implementation = ErrorResponse::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            )
        ]
    )
    fun listCategories(): ResponseEntity<List<ListCategoriesDto>> {
        return ResponseEntity.ok(listCategoriesUseCase.ListAllCategories())
    }
}