package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.CurrencyType
import com.shoprite.api.domain.MonetaryAmount
import com.shoprite.api.domain.UserName
import com.trendyol.kediatr.Command

data class DepositCommand(
    val userName: UserName,
    val amount: MonetaryAmount,
    val currencyType: CurrencyType
) : Command

