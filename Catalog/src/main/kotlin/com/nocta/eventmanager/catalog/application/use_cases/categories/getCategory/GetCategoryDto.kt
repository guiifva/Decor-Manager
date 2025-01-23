package com.nocta.eventmanager.catalog.application.use_cases.categories.getCategory

import java.util.*

data class GetCategoryDto(var id: UUID? = null, var name: String, var description: String? = null)