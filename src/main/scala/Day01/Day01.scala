package Day01

import scala.io.Source

object Day01 {
    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day01/input")
        val input = source.getLines().toList.map(_.toInt)
        source.close()

        println(part1(input) + " (Correct: 1791)")
        println(part2(input) + " (Correct: 1822)")
    }

    def part1(xs: List[Int]): Int = {
        xs.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
    }

    def part2(xs: List[Int]): Int = {
        xs.sliding(3).map(_.sum).toList.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
    }
}
