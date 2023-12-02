import kotlin.math.max

val linePattern = "Game (\\d+): (.*)".toRegex();
val cubePattern = "(?<number>\\d+)\\s+(?<color>\\w+)".toRegex()

fun main() {
    {}::class.java.getResourceAsStream("02_a")!!.bufferedReader().use { reader -> reader.use {
        val l = it.lines().map { line ->
            linePattern.matchEntire(line)?.let {matchResult ->
                val minBag = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

                val draws = matchResult.groupValues[2].split("; ")
                draws.forEach { draw ->
                    draw.split(", ").map { cubePattern.matchEntire(it) }.forEach { matchResult ->
                        val number = matchResult!!.groups["number"]?.value?.toInt() ?: 0;
                        val color = matchResult.groups["color"]?.value!!

                        minBag[color] = max(minBag[color]?: 0, number)
                    }
                }

                minBag.values.reduce(Int::times)
            }
        }
        println(l.reduce(Int::plus).orElse(0))
    }}
}
