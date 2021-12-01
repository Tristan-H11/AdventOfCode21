package Day1

import scala.io.Source

object Day1 {
    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day1/input")
        val xs = source.getLines().toList.map(_.toInt)
        source.close()

        part1(xs)
        part2(xs)
    }

    private def part1(lines: List[Int]): Unit = {
        val func = lines.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
        println(
            func + " (1791)"
        )
    }

    private def part2(lines: List[Int]): Unit = {
        val func: Int = lines.sliding(3).map(_.sum).toList.sliding(2).count {
            case List(a, b) => a < b
            case _ => false
        }
        println(func + " (1822)")
    }

}
