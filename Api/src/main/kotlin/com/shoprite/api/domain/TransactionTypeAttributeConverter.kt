package com.shoprite.api.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class TransactionTypeAttributeConverter : AttributeConverter<TransactionType, Int> {
    override fun convertToDatabaseColumn(attribute: TransactionType): Int {
        return when (attribute) {
            TransactionType.DEBIT -> TransactionType.DEBIT.value
            TransactionType.CREDIT -> TransactionType.CREDIT.value
        }
    }

    override fun convertToEntityAttribute(dbData: Int?): TransactionType {

        return when (dbData) {
            TransactionType.DEBIT.value -> TransactionType.DEBIT
            TransactionType.CREDIT.value -> TransactionType.CREDIT
            else -> throw IllegalArgumentException("")
        }
    }
}