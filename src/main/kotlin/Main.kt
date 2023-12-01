fun main() {

    var sum = 0;
    {}::class.java.getResourceAsStream("01_a")!!.bufferedReader().use {
        it.forEachLine { line ->
            val first = line.first { char -> char.isDigit() }
            val last = line.last { char -> char.isDigit() }
            val result = "$first$last".toInt()
            println(result)
            sum += result
        }
    }
    println(sum)
}
