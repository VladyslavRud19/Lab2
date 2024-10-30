import Currency.EUR
import Currency.UAH
import Currency.USD

data class Trader(val name: String, val city: String)

data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)
enum class Currency {
    UAH, USD, EUR
}

fun main() {
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")
    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, UAH),
        Transaction(raoul, 2012, 10, 1000, UAH),
        Transaction(raoul, 2011, 11, 400, USD),
        Transaction(mario, 2012, 9, 710, UAH),
        Transaction(mario, 2012, 7, 700, USD),
        Transaction(alan, 2012, 4, 950, EUR)
    )
    
    // Знайти усі транзакції за 2011 рік і посортувати за вартістю (від малого до високого)
    val transactions2011 = transactions.filter { it.year == 2011 }.sortedBy { it.value }
    println("")

    // У яких унікальних містах працюють трейдери?
    val uniqueCities = transactions.map { it.trader.city }.toSet()
    println("")

    // Знайдіть усіх трейдерів із Кембриджа та відсортуйте їх за назвою
    val cambridgeTraders = transactions.map { it.trader }.filter { it.city == "Cambridge" }.toSet().sortedBy { it.name }
    println("")

    // Поверніть рядок імен усіх трейдерів, відсортованих за алфавітом
    val traderNames = transactions.map { it.trader.name }.toSet().sorted().joinToString(", ")
    println("")

    // Чи є трейдери в Мілані?
    val hasMilanTraders = transactions.any { it.trader.city == "Milan" }
    println("")

    // Виведіть у консоль всі значення транзакцій від трейдерів, які проживають у Кембриджі
    val cambridgeTransactions = transactions.filter { it.trader.city == "Cambridge" }.map { it.value }
    println("")

    // Знайдіть транзакцію з найбільшою вартістю
    val maxTransaction = transactions.maxByOrNull{ it.value }      
    println("")

    // Згрупуйте всі транзакції за валютою
    val transactionsByCurrency = transactions.groupBy{ it.currency }     
    println("")

    // Знайдіть суму транзакцій у гривнях
    val sumUAH = transactions.filter { it.currency == UAH }.sumOf { it.value }
    println("")

    // Створіть рядок, у якому буде виведена послідовність транзакцій відсортована за датою у наступному вигляді (<назва параметру, який потрібно вставити>)
    val sortedTransactions = transactions.sortedWith(compareBy({ it.year }, { it.month }))
    val transactionSequence = sortedTransactions.mapIndexed{index, transaction -> "${index + 1}. ${transaction.month}-${transaction.year}: ${transaction.value} ${transaction.currency}"
    }.joinToString(" -> ")         
    println("")
}
