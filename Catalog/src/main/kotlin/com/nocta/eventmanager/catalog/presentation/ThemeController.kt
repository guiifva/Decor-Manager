package com.nocta.eventmanager.catalog.presentation

import com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory.GetCategoryDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreateThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreateThemeUseCase
import com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme.CreatedThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme.GetThemeDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme.GetThemeUseCase
import com.nocta.eventmanager.catalog.application.use_cases.themes.inactivateTheme.InactivateThemeUseCase
import com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes.ListThemesDto
import com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes.ListThemesUseCase
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
@RequestMapping("/themes", produces = [MediaType.APPLICATION_JSON_VALUE])
class ThemeController(
    private val listThemesUseCase: ListThemesUseCase,
    private val createThemeUseCase: CreateThemeUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val inactivateThemeUseCase: InactivateThemeUseCase
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
    fun createTheme(
        @RequestBody createThemeDto: CreateThemeDto, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CreatedThemeDto> {
        val createdThemeDto = createThemeUseCase.execute(createThemeDto)
        val uri = uriBuilder.path("/themes/${createdThemeDto.id}").build().toUri()

        return ResponseEntity.created(uri).body(createdThemeDto)
    }


    @GetMapping
    @Operation(summary = "List themes", description = "A list of themes")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Theme list",
                content = [Content(
                    array = ArraySchema(schema = Schema(implementation = ListThemesDto::class)),
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
    fun listThemes(): ResponseEntity<List<ListThemesDto>> {
        return ResponseEntity.ok(listThemesUseCase.execute())
    }

    @GetMapping("/{id}")
    fun getThemeById(@PathVariable("id") id: UUID): ResponseEntity<GetThemeDto> {
        return ResponseEntity.ok(getThemeUseCase.execute(id))
    }

    @DeleteMapping("/{id}")
    fun inactivateTheme(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        inactivateThemeUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}