package Day02

import org.scalatest.funsuite.AnyFunSuite

import scala.io.{BufferedSource, Source}

class Day02Test extends AnyFunSuite {
    val source: BufferedSource = Source.fromFile("src/main/scala/Day02/input")
    val input: List[String] = source.getLines().toList
    source.close()

    test("Day02, Part1"){
        assert(Day02.part1(input) == 2322630)
        println("--- Day 2: Dive! --- Part1 result: 2322630")
    }
    test("Day02, Part2"){
        assert(Day02.part2(input) == 2105273490)
        println("--- Day 2: Dive! --- Part2 result: 2105273490")
    }
}
