package com.shoprite.api.queries.report

import com.shoprite.api.infrastructure.TransactionsRepository
import com.shoprite.api.infrastructure.UserRepository
import com.trendyol.kediatr.QueryHandler
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ReportQueryHandler(
    val userRepository: UserRepository
) : QueryHandler<ReportQuery, ReportResponse> {
    override suspend fun handle(query: ReportQuery): ReportResponse {
        val user = userRepository.findByUserName(query.userName.value)
        val account = user.accounts.firstOrNull { account -> account.id == query.account.value }
        requireNotNull(account) {
            "Account does not exist or does not belong to user ${query.userName.value}"
        }
        val transactions = account.transactions.filter {
            true // TODO filer paging
        }
        return ReportResponse(transactions)
    }
}