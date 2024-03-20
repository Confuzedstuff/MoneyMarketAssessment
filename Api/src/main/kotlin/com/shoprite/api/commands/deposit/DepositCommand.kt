package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.AccountNumber
import com.shoprite.api.domain.CurrencyType
import com.shoprite.api.domain.MonetaryAmount
import com.shoprite.api.domain.UserName
import com.trendyol.kediatr.Command
import java.net.URL

data class DepositCommand(
    val userName: UserName,
    val amount: MonetaryAmount,
    val currencyType: CurrencyType
) : Command

data class GenerateReportCommand(
    val userName: UserName,
    val accountId: AccountNumber,
    val callbackUrl: URL
) : Command

