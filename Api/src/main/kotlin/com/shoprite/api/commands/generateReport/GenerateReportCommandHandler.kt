package com.shoprite.api.commands.generateReport

import com.shoprite.api.commands.deposit.DepositCommand
import com.shoprite.api.queries.report.ReportQuery
import com.shoprite.api.services.WebhookCallbackService
import com.trendyol.kediatr.CommandHandler
import com.trendyol.kediatr.Mediator
import org.springframework.stereotype.Service

@Service
class GenerateReportCommandHandler(
    val mediator: Mediator,
    val webhookCallbackService: WebhookCallbackService
) : CommandHandler<GenerateReportCommand> {
    override suspend fun handle(command: GenerateReportCommand) {
        // TODO place message onto queue that is handled later by a background worker running the below code

        val query = ReportQuery(command.userName, command.accountId)
        val report = mediator.send(query)
        webhookCallbackService.send(command.callbackUrl, report)
    }
}