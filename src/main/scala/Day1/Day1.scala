package Day1

import scala.io.Source

object Day1 {
    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day1/input")
        val lines = source.getLines().toList.map(y => y.toInt)
        source.close()

        part1(lines)
        part2(lines)
    }

    private def part1(lines: List[Int]): Unit = {
        var result = 0
        for (i <- 0 until lines.length - 1) {
            if (lines(i) < lines(i + 1)) result += 1
            //            println(lines(i) + " vs " + lines(i + 1) + (if (lines(i) < lines(i + 1)) " X" else ""))
        }
        println(result + " (1791)")
    }

    private def part2(lines: List[Int]): Unit = {
        var result = 0
        for (i <- 0 until lines.length - 1) {
            if (getTrippleSum(lines, i) < getTrippleSum(lines, i + 1)) result += 1
            //            println(lines(i) + " vs " + lines(i + 1) + (if (lines(i) < lines(i + 1)) " X" else ""))
        }
        println(result + " (1822)")
    }

    private def getTrippleSum(lines: List[Int], index: Int): Int = {
        if (index + 2 >= lines.length) return 0
        lines(index) + lines(index + 1) + lines(index + 2)
    }
}
