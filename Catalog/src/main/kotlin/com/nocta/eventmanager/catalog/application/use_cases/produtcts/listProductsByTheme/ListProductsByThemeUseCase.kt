package com.nocta.eventmanager.catalog.application.use_cases.produtcts.listProductsByCategory

import java.util.*

interface ListProductsByThemeUseCase {
    fun execute(themeId: UUID): List<ListProductsByThemeDto>
}