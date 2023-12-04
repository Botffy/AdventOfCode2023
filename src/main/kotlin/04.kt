fun main() {
    {}::class.java.getResourceAsStream("04_a")!!.bufferedReader().use { reader ->
        val copies = mutableMapOf(1 to 1)

        val cards = reader.lines().map { line ->
            val parts = line.split(":");
            val gameNumber = parts[0].substring("Card ".length).trim().toInt()
            val numbers = parts[1].split("|")
            val winningNumbers = numbers[0].trim().split("\\s+".toRegex()).map { it.trim() }.map { it.toInt() }.toSet();
            val actualNumbers =
                numbers[1].trim().split("\\s+".toRegex()).map { it.trim() }.map { it.toInt() }.toMutableSet();

            actualNumbers.retainAll(winningNumbers)
            copies.put(gameNumber, 1)
            Pair(gameNumber, actualNumbers.size)
        }.toList()

        cards.forEach {
            println(it)
            (it.first + 1..it.first + it.second).forEach { cardNumber ->
                println("$cardNumber "+ copies[cardNumber] + " " + copies[it.first])
                copies[cardNumber] = copies[cardNumber]!! + copies[it.first]!!
            }
            println(copies)
        }


        println(copies.values.sum())
    }
}