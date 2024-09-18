package com.nocta.eventmanager.catalog.infrastructure.repositories

import com.nocta.eventmanager.catalog.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {
    fun findByIdIn(categoryIds: List<UUID>?): List<Category>
    fun findByNameIgnoreCase(name: String): Category?
}