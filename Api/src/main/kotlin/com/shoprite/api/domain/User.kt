package com.shoprite.api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class User(
    @Id
    val id: Long,
    @OneToMany
    val accounts: Set<Account>
)