package com.nocta.eventmanager.catalog.domain.exceptions

import java.util.*

class ThemeNotFoundException(val id: UUID) : NotFoundException("Theme $id not found")