package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.CurrencyType
import com.trendyol.kediatr.Command
import java.math.BigDecimal

data class DepositCommand(
    val amount: BigDecimal,
    val currencyType: CurrencyType
) : Command