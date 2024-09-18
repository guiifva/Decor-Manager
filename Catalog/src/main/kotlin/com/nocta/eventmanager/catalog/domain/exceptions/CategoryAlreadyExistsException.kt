package com.nocta.eventmanager.catalog.domain.exceptions

class CategoryAlreadyExistsException(name: String) : DomainException("Category with name $name already exists")