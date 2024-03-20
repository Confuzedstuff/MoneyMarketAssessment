package com.shoprite.api.commands.transfer

import com.shoprite.api.domain.Transaction
import com.shoprite.api.domain.TransactionType
import com.shoprite.api.infrastructure.TransactionsRepository
import com.shoprite.api.infrastructure.UserRepository
import com.shoprite.api.services.ConversionRateService
import com.trendyol.kediatr.CommandHandler
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TransferCommandHandler(
    val userRepository: UserRepository,
    val transactions: TransactionsRepository,
    val conversionRateService: ConversionRateService
) : CommandHandler<TransferCommand> {
    @Transactional
    override suspend fun handle(command: TransferCommand) {
        val user = userRepository.findByUserName(command.userName.value)
        val sourceAccount = user.accounts.first { x -> x.accountType.type == command.currencyType.value }
        val sourceTotal = sourceAccount.transactions.map { t ->
            if (t.transactionTypeId == TransactionType.DEBIT) -t.amount else t.amount
        }.sumOf { x -> x }
        require(sourceTotal >= command.amount.value) {
            "Insufficient funds in source account"
        }

        val targetAccount = user.accounts.firstOrNull { a -> a.id == command.targetAccount.value }
        require(targetAccount != null) {
            "Target account ${command.targetAccount.value} does not exist"
        }

        val convertedAmount =
            conversionRateService.convert(command.amount, sourceAccount.accountType, targetAccount.accountType)

        val debit = Transaction.createDebit(sourceAccount.id, command.amount)
        val credit = Transaction.createCredit(targetAccount.id, convertedAmount)
        val transactions = listOf(debit, credit)
        this.transactions.saveAll(transactions)
    }
}