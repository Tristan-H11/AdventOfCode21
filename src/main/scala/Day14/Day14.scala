package Day14

import scala.io.Source
import eu.sim642.adventofcodelib.IterableImplicits._
import eu.sim642.adventofcodelib.IteratorImplicits._

object Day14 {

    case class Polymer(elements: Map[Char, Long], pairs: Map[(Char, Char), Long])
    type Rules = Map[(Char, Char), Char]
    case class Input(polymer: Polymer, rules: Rules)

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day14/input")
        val input: String = source.mkString.trim
        source.close()

        println(part1(input) + " (correct: 2937)")
        println(part2(input) + " (correct: 3390034818249)")
    }

    def part1(input: String): Long = {
        elementCountDifference(parseInput(input))
    }

    def part2(input: String): Long = {
        elementCountDifference(parseInput(input), 40)
    }

    def parseInput(input: String): Input = {
        val Seq(polymerStr, rulesStr) = input.split("\r\n\r\n", 2).toSeq
        val polymer = parsePolymer(polymerStr)
        val rules = rulesStr.linesIterator.map(parseRule).toMap
        Input(polymer, rules)
    }

    def applyRules(polymer: Polymer, rules: Rules): Polymer = {
        val newPairs = polymer.pairs.iterator.flatMap({ case (pair@(a, b), cnt) =>
            val c = rules(pair)
            Iterator((a, c) -> cnt, (c, b) -> cnt)
        }).groupMapReduce(_._1)(_._2)(_ + _)
        val newElements = polymer.pairs.foldLeft(polymer.elements)({ case (elements, (pair, cnt)) =>
            val c = rules(pair)
            elements + (c -> (elements.getOrElse(c, 0L) + cnt))
        })
        Polymer(newElements, newPairs)
    }

    def iterateRules(initialPolymer: Polymer, rules: Rules): Iterator[Polymer] = {
        Iterator.iterate(initialPolymer)(applyRules(_, rules))
    }

    def elementCountDifference(input: Input, after: Int = 10): Long = {
        val Input(initialPolymer, rules) = input
        val finalPolymer = iterateRules(initialPolymer, rules)(after)
        val finalElementCounts = finalPolymer.elements.values
        finalElementCounts.max - finalElementCounts.min
    }

    def parsePolymer(s: String): Polymer = {
        val elements = s.iterator.groupMapReduce(identity)(_ => 1L)(_ + _)
        val pairs = s.iterator.zipWithTail.groupMapReduce(identity)(_ => 1L)(_ + _)
        Polymer(elements, pairs)
    }

    def parseRule(s: String): ((Char, Char), Char) = {
        val Seq(left, right) = s.split(" -> ", 2).toSeq
        (left(0), left(1)) -> right(0)
    }
}
