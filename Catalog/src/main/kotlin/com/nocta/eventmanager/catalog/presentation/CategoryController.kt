package com.nocta.eventmanager.catalog.presentation

import com.nocta.eventmanager.catalog.application.use_cases.categories.categoryNameAlreadyExists.CategoryNameAlreadyExistsUseCase
import com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory.CreateCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.createCategory.CreateCategoryUseCase
import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryUseCase
import com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories.ListCategoriesDto
import com.nocta.eventmanager.catalog.application.use_cases.categories.listCategories.ListCategoriesUseCase
import com.nocta.eventmanager.catalog.application.use_cases.themes.inactivateTheme.InactivateCategoryUseCase
import com.nocta.eventmanager.catalog.presentation.models.ErrorMessageModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/categories", produces = [MediaType.APPLICATION_JSON_VALUE])
class CategoryController(
    private val listCategoriesUseCase: ListCategoriesUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val categoryNameAlreadyExistsUseCase: CategoryNameAlreadyExistsUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val inactivateCategoryUseCase: InactivateCategoryUseCase
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
                    schema = Schema(implementation = ErrorMessageModel::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = [Content(
                    schema = Schema(implementation = ErrorMessageModel::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            )
        ]
    )
    @PostMapping
    fun insertCategory(
        @RequestBody createCategoryDto: CreateCategoryDto, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<GetCategoryDto> {
        val createdCategoryDto = createCategoryUseCase.execute(createCategoryDto)
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
                    schema = Schema(implementation = ErrorMessageModel::class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            )
        ]
    )
    fun listCategories(): ResponseEntity<List<ListCategoriesDto>> {
        return ResponseEntity.ok(listCategoriesUseCase.execute())
    }

    @GetMapping("/exists/{name}")
    @Operation(summary = "Check if category name already exists", description = "Check if category name already exists")
    fun categoryNameAlreadyExists(@PathVariable name: String): ResponseEntity<Any> {
        return ResponseEntity.ok(object { val exists = categoryNameAlreadyExistsUseCase.execute(name)})
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Get category by id")
    fun getCategoryById(@PathVariable id: UUID): ResponseEntity<GetCategoryDto> {
        return ResponseEntity.ok(getCategoryUseCase.execute(id))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Inactivate category by id", description = "Inactivate category by id")
    fun inactivateCategoryById(@PathVariable id: UUID): ResponseEntity<Any> {
        inactivateCategoryUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}