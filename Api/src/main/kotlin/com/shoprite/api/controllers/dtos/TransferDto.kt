package com.shoprite.api.controllers.dtos

import java.math.BigDecimal

data class TransferDto(
    val amount: BigDecimal,
    val currencyType: String,
    val destinationAccount: Long
)