package com.shoprite.api.commands.deposit

import com.shoprite.api.domain.UserRepository
import com.trendyol.kediatr.CommandHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class DepositCommandHandler(val repo: UserRepository) : CommandHandler<DepositCommand> {
    override suspend fun handle(command: DepositCommand) {
        withContext(Dispatchers.IO) {
            val res  =repo.findById(1);
            println(res)

        }
        println(command)
    }
}