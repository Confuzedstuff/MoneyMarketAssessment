package com.shoprite.api.domain

import jakarta.persistence.*
import java.math.BigDecimal
@Entity
@Table(name = "transactions")
class Transaction(
    val transactionTypeId: TransactionType,
    @Column(name = "account_id")
    val accountId: Long,
    val amount: BigDecimal,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null
}