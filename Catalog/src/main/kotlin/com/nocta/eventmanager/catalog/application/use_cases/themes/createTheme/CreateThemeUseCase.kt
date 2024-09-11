package com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme

interface CreateThemeUseCase {
    fun createTheme(createThemeDto: CreateThemeDto): CreatedThemeDto
}