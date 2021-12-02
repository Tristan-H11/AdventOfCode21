package Day2

import scala.io.Source

object Day2 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day2/input")
        val input = source.getLines().toList
        source.close()

        println(part1(input) + " (correct: 2322630)")
        println(part2(input) + " (correct: 2105273490)")
    }

    def part1(xs: List[String]): Int = {
        var forward = 0
        var down = 0
        xs.foreach(f = e => e.split(" ").head match {
            case "forward" => forward += e.split(" ")(1).toInt
            case "up" => down -= e.split(" ")(1).toInt
            case "down" => down += e.split(" ")(1).toInt
        })
        forward * down
    }

    def part2(xs: List[String]): Int = {
        var forward = 0
        var down = 0
        var aim = 0
        xs.foreach(e => e.split(" ").head match {
            case "forward" =>
                forward += e.split(" ")(1).toInt
                down += aim * e.split(" ")(1).toInt
            case "up" => aim -= e.split(" ")(1).toInt
            case "down" => aim += e.split(" ")(1).toInt
        })
        forward * down
    }
}
