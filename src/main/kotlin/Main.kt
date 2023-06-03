fun main() {
 try {
  val commission = calculateCommission("VK Pay", previousTransfersAmount = 250000, transferAmount = 1000, transferType = "VK Pay")
  println("Комиссия за перевод: $commission рублей")
 } catch (e: Exception) {
  println("Ошибка: ${e.message}")
 }
}
