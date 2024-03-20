package com.shoprite.api.services

import com.shoprite.api.domain.AccountType
import com.shoprite.api.domain.MonetaryAmount
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ConversionRateService {
    // note this lookup should be looked up from the database
    // these values are double just as an example
    val conversionRates = mapOf(
        "ZAR:USD" to BigDecimal.valueOf(0.053),
        "USD:ZAR" to BigDecimal.valueOf(1 / 0.053),
        "ZAR:EUR" to BigDecimal.valueOf(0.049),
        "EUR:ZAR" to BigDecimal.valueOf(1 / 0.049),
        "USD:EUR" to BigDecimal.valueOf(0.92),
        "EUR:USD" to BigDecimal.valueOf(1 / 0.92),
    )

    fun convert(amount: MonetaryAmount, sourceType: AccountType, targetType: AccountType): MonetaryAmount {
        val key = "${sourceType.type}:${targetType.type}"
        val conversionRate = MonetaryAmount(conversionRates.getValue(key))
        return amount * conversionRate
    }
}