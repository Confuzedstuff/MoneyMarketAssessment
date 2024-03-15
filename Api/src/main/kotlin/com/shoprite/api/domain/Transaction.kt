package com.shoprite.api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class Transaction(
    @Id
    val id: Long,
    val transactionType: TransactionType,
    val accountNumber: Long,
    val amount: BigDecimal
)