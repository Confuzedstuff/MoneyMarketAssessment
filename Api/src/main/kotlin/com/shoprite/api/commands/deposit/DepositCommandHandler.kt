package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.UserRepository
import com.trendyol.kediatr.CommandHandler
import org.springframework.stereotype.Service

@Service
class DepositCommandHandler(repo: UserRepository) : CommandHandler<DepositCommand> {
    override suspend fun handle(command: DepositCommand) {
        println(command)
    }
}