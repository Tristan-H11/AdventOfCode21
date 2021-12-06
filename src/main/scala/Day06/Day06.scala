package Day06

import scala.io.Source
import eu.sim642.adventofcodelib.IteratorImplicits._

object Day06 {

    /*
     * Credits to https://github.com/sim642 for this solution.
     */

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day06/input")
        val input = source.mkString.trim
        val fish: Seq[Int] = input.split(",").toSeq.map(_.toInt)

        println(part1(fish) + " (correct: 356190)")
        println(part2(fish) + " (correct: 1617359101538)")
    }

    def part1(xs: Seq[Int]): Long = {
        countFishy(xs, 80)
    }

    def part2(xs: Seq[Int]): Long = {
        countFishy(xs, 256)
    }

    def inputToVector(input: Seq[Int]): Vector[Long] = Vector.tabulate(9)(i => input.count(_ == i)) // largest value is 8

    def growThoseFish(state: Vector[Long]): Vector[Long] = {
        val head +: tail = state
        tail.updated(6, tail(6) + head) :+ head
    }

    def countFishy(input: Seq[Int], daysToCount: Int): Long = {
        Iterator.iterate(inputToVector(input))(growThoseFish)(daysToCount).sum
    }
}
