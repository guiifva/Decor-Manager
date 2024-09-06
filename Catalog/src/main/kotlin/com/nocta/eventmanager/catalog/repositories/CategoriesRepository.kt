package com.nocta.eventmanager.catalog.repositories

import com.nocta.eventmanager.catalog.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoriesRepository : JpaRepository<Category, UUID> {
}