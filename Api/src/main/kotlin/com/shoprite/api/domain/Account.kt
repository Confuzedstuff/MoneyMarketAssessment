package com.shoprite.api.domain

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
class Account(
    @ManyToOne
    @JoinColumn(name = "account_type_id")
    val accountType: AccountType,
    @OneToMany
    @JoinColumn(name = "account_id")
    val transactions: Set<Transaction>,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0

)

