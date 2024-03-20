package com.shoprite.api.commands.transfer

import com.shoprite.api.domain.AccountNumber
import com.shoprite.api.domain.CurrencyType
import com.shoprite.api.domain.MonetaryAmount
import com.shoprite.api.domain.UserName
import com.trendyol.kediatr.Command
import java.math.BigDecimal

data class TransferCommand(
    val userName: UserName,
    val amount: MonetaryAmount,
    val currencyType: CurrencyType,
    val targetAccount: AccountNumber
) : Command