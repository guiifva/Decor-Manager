package com.nocta.eventmanager.catalog.infrastructure.repositories

import com.nocta.eventmanager.catalog.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {
    fun findByIdIn(categoryIds: List<UUID>?): List<Category>

    @Query("""
    SELECT c FROM Category c 
    WHERE LOWER(c.name) = LOWER(:name) 
    AND (:id IS NULL OR c.id != :id)
""")
    fun findByNameAndIdIgnoreCase(name: String, id: UUID? = null): Category?
}