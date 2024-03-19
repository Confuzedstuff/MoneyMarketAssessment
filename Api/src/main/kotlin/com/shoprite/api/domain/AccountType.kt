package com.shoprite.api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "account_types")
class AccountType(
    @Id
    val id: Long,
    val type: String,
)