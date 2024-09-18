package com.nocta.eventmanager.catalog.domain.exceptions

import java.util.*

class CategoryNotFoundException(val id : UUID) : NotFoundException("Category: '$id' not found")