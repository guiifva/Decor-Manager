package com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme

interface CreateThemeUseCase {
    fun execute(createThemeDto: CreateThemeDto): CreatedThemeDto
}