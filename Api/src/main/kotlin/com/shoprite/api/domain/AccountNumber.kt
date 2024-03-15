package com.shoprite.api.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@JvmInline
value class AccountNumber(val value: Int) {
    init {
        require(value > 0) {
            "Account Number must be greater than 0"
        }
    }
}

@Repository
interface UserRepository : JpaRepository<User?, Long?>
