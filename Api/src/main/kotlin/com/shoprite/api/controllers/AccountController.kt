package com.shoprite.api.controllers

import com.shoprite.api.commands.deposit.DepositCommand
import com.shoprite.api.commands.transfer.TransferCommand
import com.shoprite.api.controllers.dtos.DepositDto
import com.shoprite.api.controllers.dtos.ReportDto
import com.shoprite.api.controllers.dtos.TransferDto
import com.shoprite.api.domain.AccountNumber
import com.shoprite.api.domain.CurrencyType
import com.shoprite.api.domain.MonetaryAmount
import com.shoprite.api.queries.report.ReportQuery
import com.shoprite.api.queries.report.ReportResponse
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
        val userName = userService.getUserNameFromJwt(jwt)
        val currencyType = CurrencyType(deposit.currencyType)
        val amount = MonetaryAmount(deposit.amount)
        val command = DepositCommand(userName, amount, currencyType)
        mediator.send(command)
    }

    @PostMapping("transfer")
    suspend fun transfer(
        @RequestBody transfer: TransferDto,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        val userName = userService.getUserNameFromJwt(jwt)
        val currencyType = CurrencyType(transfer.currencyType)
        val amount = MonetaryAmount(transfer.amount)
        val destinationAccount = AccountNumber(transfer.destinationAccount)
        val command = TransferCommand(userName, amount, currencyType, destinationAccount)
        mediator.send(command)
    }

    @GetMapping("report")
    suspend fun report(
        @RequestBody body: ReportDto,
        @AuthenticationPrincipal jwt: Jwt
    ): ReportResponse {
        val userName = userService.getUserNameFromJwt(jwt)
        val account = AccountNumber(body.account)
        val command = ReportQuery(userName, account)
        val response = mediator.send(command)
        return response // TODO introduce mapping to a dto
    }
}