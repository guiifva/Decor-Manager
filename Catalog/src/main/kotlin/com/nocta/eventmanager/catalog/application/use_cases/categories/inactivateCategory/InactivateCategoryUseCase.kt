package com.nocta.eventmanager.catalog.application.use_cases.themes.inactivateTheme

import java.util.*

interface InactivateCategoryUseCase {
    fun execute(id: UUID)
}