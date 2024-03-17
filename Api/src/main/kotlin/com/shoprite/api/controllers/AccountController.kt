package com.shoprite.api.controllers

import com.shoprite.api.commands.deposit.DepositCommand
import com.shoprite.api.controllers.dtos.DepositDto
import com.shoprite.api.domain.CurrencyType
import com.trendyol.kediatr.Mediator
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val mediator: Mediator
) {
    @GetMapping("test")
    fun test() {
        println("hi")
    }

    @PostMapping("deposit")
    suspend fun deposit(
        @RequestBody deposit: DepositDto
    ) {
        val currencyType = CurrencyType(deposit.currencyType)
        val command = DepositCommand(deposit.amount, currencyType)
        mediator.send(command)
    }
}

