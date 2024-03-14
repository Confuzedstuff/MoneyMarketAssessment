package com.shoprite.api.domain

@JvmInline
value class CurrencyType private  constructor(val value: String) {
    init {
        require(value.length == 3){
            "Currency Type must be 3 characters long"
        }
    }

    companion object {
        operator fun invoke(value: String?): CurrencyType {
            requireNotNull(value) { "Currency Type cannot be null" }
            return CurrencyType(value.uppercase())
        }
    }
}