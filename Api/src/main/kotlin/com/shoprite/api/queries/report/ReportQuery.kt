package com.shoprite.api.queries.report

import com.shoprite.api.domain.AccountNumber
import com.shoprite.api.domain.Transaction
import com.shoprite.api.domain.UserName
import com.trendyol.kediatr.Query

data class ReportQuery(
    val userName: UserName,
    val account: AccountNumber

) : Query<ReportResponse>

data class ReportResponse(val transactions: List<Transaction>)