package com.shoprite.api.domain


@JvmInline
value class AccountNumber(val value: Long) {
    init {
        require(value >= 0) {
            "Invalid account number"
        }
    }
}

