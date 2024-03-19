package com.shoprite.api.infrastructure

import com.shoprite.api.domain.Account
import com.shoprite.api.domain.Transaction
import com.shoprite.api.domain.TransactionType
import com.shoprite.api.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(userName: String): User
}

@Repository
interface TransactionsRepository : JpaRepository<Transaction, Long> {
}