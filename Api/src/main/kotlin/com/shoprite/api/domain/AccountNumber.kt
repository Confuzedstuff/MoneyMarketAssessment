package com.shoprite.api.domain

@JvmInline
value class AccountNumber(val value: Int) {
    init {
        require(value > 0) {
            "Account Number must be greater than 0"
        }
    }
}

