package Day03

import org.scalatest.funsuite.AnyFunSuite

import scala.io.{BufferedSource, Source}

class Day03Test extends AnyFunSuite {
    val source: BufferedSource = Source.fromFile("src/main/scala/Day03/input")
    val input: Array[Array[String]] = source.getLines().toArray.map(e => e.split(""))
    source.close()

    test("Day03, Part1"){
        assert(Day03.part1(input) == 2250414)
        println("--- Day 3: Binary Diagnostic --- Part1 result: 2250414")
    }
    test("Day03, Part2"){
        assert(Day03.part2(input) == 6085575)
        println("--- Day 3: Binary Diagnostic --- Part2 result: 6085575")
    }
}


