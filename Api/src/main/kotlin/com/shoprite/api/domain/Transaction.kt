package com.shoprite.api.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
class Transaction(
    @Convert(converter = TransactionTypeAttributeConverter::class)
    val transactionTypeId: TransactionType,
    @Column(name = "account_id")
    val accountId: Long,
    val amount: BigDecimal,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0
) {

    companion object {
        fun createDebit(accountId: Long, amount: MonetaryAmount): Transaction {
            return Transaction(TransactionType.DEBIT, accountId, amount.value)
        }
        fun createCredit(accountId: Long, amount: MonetaryAmount): Transaction {
            return Transaction(TransactionType.CREDIT, accountId, amount.value)
        }
    }
}