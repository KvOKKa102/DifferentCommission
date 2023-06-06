fun calculateCommission(cardType: String, previousTransfersAmount: Int = 0,
                        transferAmount: Int, transferType: String): Int {
    val isMastercardOrMaestro = cardType == "Mastercard" || cardType == "Maestro"
    val isVisaOrMir = cardType == "Visa" || cardType == "Mir"

    if (transferType == "card") {
        val dailyLimit = 150_000
        val monthlyLimit = 600_000

        if (transferAmount > dailyLimit) {
            throw Exception("Сумма перевода превышает суточный лимит")
        }
        val totalSum = previousTransfersAmount + transferAmount
        if (totalSum > monthlyLimit) {
            throw Exception("Сумма переводов в текущем месяце превышает месячный лимит")
        }
    }

    if (transferType == "VK Pay") {
        val singleLimit = 15_000
        val monthlyLimit = 40_000

        if (transferAmount > singleLimit) {
            throw Exception("Сумма перевода на VK Pay превышает максимальную сумму за один раз")
        }
        val totalSum = previousTransfersAmount + transferAmount
        if (totalSum > monthlyLimit) {
            throw Exception("Сумма переводов на VK Pay в текущем месяце превышает месячный лимит")
        }
    }

    if (isMastercardOrMaestro) {
        val transferSumLimit = 75_000
        val totalSum = previousTransfersAmount + transferAmount

        if (totalSum > transferSumLimit) {
            return (transferAmount * 0.006 + 20).toInt()
        } else {
            return 0
        }
    }

    if (isVisaOrMir) {
        val commissionPercent = 0.0075
        val minCommission = 35

        val commission = transferAmount * commissionPercent
        if (commission < minCommission) {
            return minCommission
        } else {
            return commission.toInt()
        }
    }
    return 0
}