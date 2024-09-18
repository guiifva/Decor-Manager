package com.nocta.eventmanager.catalog.application.use_cases.themes.getTheme

import java.util.*

interface GetThemeUseCase {
    fun execute(id: UUID): GetThemeDto
}