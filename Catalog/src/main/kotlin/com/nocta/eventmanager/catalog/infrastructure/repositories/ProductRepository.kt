package com.nocta.eventmanager.catalog.infrastructure.repositories

import com.nocta.eventmanager.catalog.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByName(productName: String): Product?
}