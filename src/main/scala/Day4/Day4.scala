package Day4

import scala.io.Source

object Day4 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day3/input")
        val input = source.getLines().toList
        source.close()

        println(part1(input) + " (correct: ???)")
        println(part2(input) + " (correct: ???)")
    }

    def part1(xs: List[String]): Int = {
        ???
    }

    def part2(xs: List[String]): Int = {
        ???
    }
}
