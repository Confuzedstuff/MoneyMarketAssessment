package com.shoprite.api.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    val userName: String,
    @OneToMany
    @JoinColumn(name = "user_id")
    val accounts: Set<Account>,
    @Id @GeneratedValue var id: Long? = null

)