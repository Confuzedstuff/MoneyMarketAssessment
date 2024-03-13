package com.shoprite.api.controllers

import com.shoprite.api.controllers.dtos.DepositDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController {

    @PostMapping("/{accountNumber}/deposit")
    fun deposit(
        @PathVariable accountNumber: String, @RequestBody deposit: DepositDto
    ) {
        println(accountNumber)
        println(deposit)
    }
}

