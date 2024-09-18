package com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes

interface ListThemesUseCase {
    fun execute(): List<ListThemesDto>
}