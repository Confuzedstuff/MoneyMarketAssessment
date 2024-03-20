package com.shoprite.api.domain

import java.math.BigDecimal

@JvmInline
value class MonetaryAmount(val value: BigDecimal) {
    init {
        require(value > BigDecimal.ZERO) {
            "Monetary amount must be greater than 0"
        }
    }
}