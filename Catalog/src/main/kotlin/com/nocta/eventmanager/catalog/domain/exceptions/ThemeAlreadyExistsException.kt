package com.nocta.eventmanager.catalog.domain.exceptions

class ThemeAlreadyExistsException(name: String) : DomainException("Theme with name $name already exists")
