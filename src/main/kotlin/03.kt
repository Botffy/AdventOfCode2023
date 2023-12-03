import kotlin.math.max
import kotlin.math.min

fun main() {
    {}::class.java.getResourceAsStream("03_a")!!.bufferedReader().use { reader ->

        val engine = Engine(reader.lines().filter({ !it.isBlank() }).toList())
        val result = engine.examine()
        println(result)

    }
}

class Engine(val map: List<String>) {
    val width = map[0].length;

    fun examine(): Int {
        var currentNumber = "";
        var gearsAroundCurrentNumber = mutableListOf<Int>()
        val numbersByGears = mutableMapOf<Int, MutableSet<Int>>()

        for ((y, line) in map.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char.isDigit()) {
                    currentNumber += char
                    val gearAround = hasGearAround(x, y)
                    if (gearAround >= 0) {
                        gearsAroundCurrentNumber.add(gearAround)
                    }
                } else {
                    val uniqueGears = gearsAroundCurrentNumber.distinct();
                    uniqueGears.forEach {
                        numbersByGears.getOrPut(it) { mutableSetOf() }.add(currentNumber.toInt())
                    }

                    gearsAroundCurrentNumber = mutableListOf();
                    currentNumber = "";
                }
            }
        }

        return numbersByGears.values.stream().filter { it.size == 2 }.mapToInt { it.stream().reduce { x, y -> x*y}.orElse(0) }.sum()
    }

    private fun hasGearAround(originX: Int, originY: Int): Int {
        for (y in (max(originY-1, 0)..min(originY+1, map.size-1))) {
            for (x in (max(originX-1, 0)..min(originX+1, map[y].length-1))){
                if (map[y][x] == '*'){
                    return y * width + x
                }
            }
        }

        return -1;
    }
}
