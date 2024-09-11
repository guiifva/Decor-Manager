package com.nocta.eventmanager.catalog.application.use_cases.themes.listThemes

import com.nocta.eventmanager.catalog.domain.enums.Size
import java.util.*

data class ListThemesDto(val id: UUID,
                         val name: String,
                         val size: Size,
                         val description: String,
                         var active: Boolean = true,
                         var price: Double? = null)
