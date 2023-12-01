package com.example.database.table

import org.jetbrains.exposed.dao.id.UUIDTable

object User: UUIDTable("users") {
    val userName=varchar("name",40)
    val gmail=varchar("gmail",45).uniqueIndex()
    val password=varchar("password",40)
}