package com.nocta.eventmanager.catalog.application.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}