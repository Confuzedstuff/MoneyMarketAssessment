package com.shoprite.api.controllers

import com.shoprite.api.commands.deposit.DepositCommand
import com.shoprite.api.controllers.dtos.DepositDto
import com.shoprite.api.domain.CurrencyType
import com.shoprite.api.services.UserService
import com.trendyol.kediatr.Mediator
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    val mediator: Mediator,
    val userService: UserService
) {
    @PostMapping("deposit")
    suspend fun deposit(
        @RequestBody deposit: DepositDto,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        val userName = userService.getUserNameFromJwt(jwt);
        val currencyType = CurrencyType(deposit.currencyType)
        val command = DepositCommand(userName, deposit.amount, currencyType)
        mediator.send(command)
    }
}

