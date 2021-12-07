package Day07

import scala.io.Source

object Day07 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day07/input")
        val input = source.mkString.trim.split(",").map(_.toInt).toVector.sorted

        println(part1(input) + " (correct: 356958)")
        println(part2(input) + " (correct: 105461913)")
    }

    def part1(crabs: Vector[Int]): Int = {
        val med = crabs(crabs.size / 2)
        crabs.map(crab => (crab - med).abs).sum
    }

    def part2(crabs: Vector[Int]): Long = {
        (crabs.min to crabs.max).map(
            pos => crabs.map(
                crab => summe((pos - crab).abs)
            ).sum
        ).min
    }

    def summe(n: Int): Int = n * (n + 1) / 2

}