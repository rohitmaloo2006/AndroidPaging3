package com.rm.pagingtutorial.data.mapper

interface DomainModelConverter<T, R> {

    fun convert(data: T): R
}