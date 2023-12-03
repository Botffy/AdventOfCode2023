import kotlin.math.max
import kotlin.math.min

fun main() {
    {}::class.java.getResourceAsStream("03_a")!!.bufferedReader().use { reader ->

        val engine = Engine(reader.lines().filter({ !it.isBlank() }).toList())
        val partNumbers = engine.examine()
        println(partNumbers.sum())

    }
}

class Engine(val map: List<String>) {
    fun examine(): List<Int> {
        val result = mutableListOf<Int>()
        var currentNumber = "";
        var numberIsObjectLabel = false;

        for ((y, line) in map.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char.isDigit()) {
                    currentNumber += char;
                    if (thereIsSymbolAround(x, y)) {
                        numberIsObjectLabel = true;
                    }
                } else {
                    if (numberIsObjectLabel) {
                        result.add(currentNumber.toInt())
                    }

                    currentNumber = "";
                    numberIsObjectLabel = false;
                }
            }
        }

        return result
    }

    private fun thereIsSymbolAround(originX: Int, originY: Int): Boolean {
        for (y in (max(originY-1, 0)..min(originY+1, map.size-1))) {
            for (x in (max(originX-1, 0)..min(originX+1, map[y].length-1))){
                if (map[y][x].let { !it.isDigit() && it != '.' }){
                    return true
                }
            }
        }

        return false;
    }
}
