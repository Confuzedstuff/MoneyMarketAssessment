package com.shoprite.api.commands.generateReport

import com.shoprite.api.domain.AccountNumber
import com.shoprite.api.domain.UserName
import com.shoprite.api.domain.WebHookCallbackUrl
import com.trendyol.kediatr.Command
import java.net.URL

data class GenerateReportCommand(
    val userName: UserName,
    val accountId: AccountNumber,
    val callbackUrl: WebHookCallbackUrl
) : Command