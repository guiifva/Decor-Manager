package com.nocta.eventmanager.catalog.infrastructure.repositories

import com.nocta.eventmanager.catalog.domain.Theme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ThemeRepository : JpaRepository<Theme, UUID> {
    fun findByIdIn(themeIds: List<UUID>?): List<Theme>
}