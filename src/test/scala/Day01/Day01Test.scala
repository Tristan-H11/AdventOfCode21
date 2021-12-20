package Day01

import org.scalatest.funsuite.AnyFunSuite

import scala.io.{BufferedSource, Source}

class Day01Test extends AnyFunSuite {

    val source: BufferedSource = Source.fromFile("src/main/scala/Day01/input")
    val input: List[Int] = source.getLines().toList.map(_.toInt)
    source.close()

    test("Day01, Part1"){
        assert(Day01.part1(input) == 1791)
        println("--- Day 1: Sonar Sweep --- Part1 result: 1791")
    }
    test("Day01, Part2"){
        assert(Day01.part2(input) == 1822)
        println("--- Day 1: Sonar Sweep --- Part2 result: 1822")
    }
}
