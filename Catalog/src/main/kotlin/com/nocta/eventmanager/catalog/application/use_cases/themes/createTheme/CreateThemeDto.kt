package com.nocta.eventmanager.catalog.application.use_cases.themes.createTheme

import com.nocta.eventmanager.catalog.domain.enums.Size

data class CreateThemeDto(val name: String,
                          val size: Size,
                          val description: String,
                          var active: Boolean = true,
                          var price: Double? = null)
