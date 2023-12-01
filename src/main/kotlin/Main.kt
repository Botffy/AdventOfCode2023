fun main() {

    val mapping = (1..9).associateBy { it.toString() } + (1..9).associateBy { listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")[it - 1] }

    val digits = mapping.keys
    var sum = 0;
    {}::class.java.getResourceAsStream("01_b")!!.bufferedReader().use { reader -> reader.use {
        it.forEachLine { line ->
            val first = digits.map { Pair(mapping[it], line.indexOf(it)) }.filter { it.second >= 0 }.minBy { it.second }.first
            val last = digits.map { Pair(mapping[it], line.lastIndexOf(it)) }.filter { it.second >= 0 }.maxBy { it.second }.first
            val result = "$first$last".toInt()
            sum += result
        }
    }}
    println(sum)
}
