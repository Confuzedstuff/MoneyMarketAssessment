package com.shoprite.api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class User(
    @Id
    val id: Long,
    val userName: String,
    @OneToMany
    val accounts: Set<Account>
)