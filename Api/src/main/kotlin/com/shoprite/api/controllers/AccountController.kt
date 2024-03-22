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
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    val mediator: Mediator,
    val userService: UserService
) {
    @Operation(summary = "Deposit into account")
    @PostMapping("deposit")
    suspend fun deposit(
        @RequestBody requestBody: DepositDto,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        val userName = userService.getUserNameFromJwt(jwt)
        val currencyType = CurrencyType(requestBody.currencyType)
        val amount = MonetaryAmount(requestBody.amount)
        val command = DepositCommand(userName, amount, currencyType)
        mediator.send(command)
    }

    @Operation(summary = "Transfer money between user accounts")
    @PostMapping("transfer")
    suspend fun transfer(
        @RequestBody requestBody: TransferDto,
        @AuthenticationPrincipal jwt: Jwt
    ) {
        val userName = userService.getUserNameFromJwt(jwt)
        val currencyType = CurrencyType(requestBody.currencyType)
        val amount = MonetaryAmount(requestBody.amount)
        val destinationAccount = AccountNumber(requestBody.destinationAccount)
        val command = TransferCommand(userName, amount, currencyType, destinationAccount)
        mediator.send(command)
    }

    @Operation(summary = "Retrieve a transaction report for account")
    @GetMapping("report/{accountId}")
    suspend fun report(
        @PathVariable accountId: Long,
        @AuthenticationPrincipal jwt: Jwt
    ): ReportResponse {
        val userName = userService.getUserNameFromJwt(jwt)
        val account = AccountNumber(accountId)
        val command = ReportQuery(userName, account)
        val response = mediator.send(command)
        return response
    }

    @Operation(summary = "Generate a transaction report for account",
        description = "")
    @PostMapping("generate-report")
    suspend fun generateReport(
        @RequestBody requestBody: ReportDto,
        @AuthenticationPrincipal jwt: Jwt
    ): ReportResponse {
        val userName = userService.getUserNameFromJwt(jwt)
        val account = AccountNumber(requestBody.account)
        val command = ReportQuery(userName, account)
        val response = mediator.send(command)
        return response // TODO introduce response mapping to a dto
    }
}