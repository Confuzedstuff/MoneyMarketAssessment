package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.Transaction
import com.shoprite.api.domain.TransactionType
import com.shoprite.api.infrastructure.TransactionsRepository
import com.shoprite.api.infrastructure.UserRepository
import com.trendyol.kediatr.CommandHandler
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class DepositCommandHandler(
    val repo: UserRepository,
    val transactions: TransactionsRepository
) : CommandHandler<DepositCommand> {
    @Transactional
    override suspend fun handle(command: DepositCommand) {
//        withContext(Dispatchers.IO) {
        val user = repo.findByUserName(command.userName.value)
        val account = user.accounts.first { x -> x.accountType.type == command.currencyType.value }
        val transactions = Transaction(TransactionType.CREDIT, account.id, command.amount)
        this.transactions.save(transactions)
    }
}