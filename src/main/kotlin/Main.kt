fun main() {

    val mapping = mapOf(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    val digits = mapping.keys
    var sum = 0;
    {}::class.java.getResourceAsStream("01_b")!!.bufferedReader().use { reader ->
        reader.forEachLine { line ->
            val first = digits.map { Pair(mapping[it], line.indexOf(it)) }.filter { it.second >= 0 }.minBy { it.second }.first
            val last = digits.map { Pair(mapping[it], line.lastIndexOf(it)) }.filter { it.second >= 0 }.maxBy { it.second }.first
            val result = "$first$last".toInt()
            sum += result
        }
    }
    println(sum)
}
