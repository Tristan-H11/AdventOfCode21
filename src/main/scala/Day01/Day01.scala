package Day01

import scala.io.Source

object Day01 {
    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day1/input")
        val input = source.getLines().toList.map(_.toInt)
        source.close()

        part1(input)
        part2(input)
    }

    private def part1(xs: List[Int]): Unit = {
        val func = xs.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
        println(func + " (Correct: 1791)")
    }

    private def part2(xs: List[Int]): Unit = {
        val func: Int = xs.sliding(3).map(_.sum).toList.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
        println(func + " (Correct: 1822)")
    }
}
