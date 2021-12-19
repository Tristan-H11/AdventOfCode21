package Day08

import scala.io.{BufferedSource, Source}

case class Entry(patterns: Seq[String], digits: Seq[String])

object Day08 {

    def main(args: Array[String]): Unit = {
        val source: BufferedSource = Source.fromFile("src/main/scala/Day08/input")
        val input: Seq[String] = source.getLines().toSeq
        source.close()
        println(part1(input) + " (correct: 412)")
        println(part2(input) + " (correct: 978171)")
    }

    def part1(input: Seq[String]): Int = {
        input.map(parseEntry).map(_.digits.count(isKnownDigit)).sum
    }

    def part2(input: Seq[String]): Int = {
        input.map(parseEntry).map(unscrambleEntry).sum
    }

    val segments: Seq[Char] = Seq('a', 'b', 'c', 'd', 'e', 'f', 'g')
    // Map *sorted* non-scrambled segment pattern to digit
    val segmentsToDigit: Map[String, Int] = Seq(
        "abcefg",
        "cf",
        "acdeg",
        "acdfg",
        "bcdf",
        "abdfg",
        "abdefg",
        "acf",
        "abcdefg",
        "abcdfg").zipWithIndex.toMap

    def parseEntry(entry: String): Entry = {
        val Array(patterns, digits) = entry.split(" \\| ")
        Entry(patterns.split(" "), digits.split(" "))
    }

    def isKnownDigit(pattern: String): Boolean = {
        pattern.length match {
            case 2 | 3 | 4 | 7 => true
            case _ => false
        }
    }

    def unscrambleEntry(entry: Entry): Int = {
        // Digit:  0 1 2 3 4 5 6 7 8 9
        // Length: 6 2 5 5 4 5 6 3 7 6
        def fromLength(x: Int): Set[Char] = {
            entry.patterns.filter(_.length == x).flatten.toSet
        }

        // Segment:     a b c d e f g
        // Occurrences: 8 6 8 7 4 9 7
        val occurrences: Map[Char, Int] = entry.patterns.flatten.groupMapReduce(identity)(_ => 1)(_ + _)

        def fromOccurrences(x: Int): Set[Char] = {
            segments.filter(occurrences(_) == x).toSet
        }

        val setCF = fromLength(2) // Digit 1 has pattern length two and uses segments c and f
        val setBCDF = fromLength(4)

        val setE = fromOccurrences(4) // Whichever letter is mapped to e will occur 4 times
        val setB = fromOccurrences(6)
        val setDG = fromOccurrences(7) // Both letters mapped to d and g occur 7 times each
        val setAC = fromOccurrences(8)
        val setF = fromOccurrences(9)

        val setC = setCF.diff(setF) // Set difference eliminates the unknowns
        val setA = setAC.diff(setC)
        val setG = setDG.diff(setBCDF)
        val setD = setDG.diff(setG)

        val unscramble: Map[Char, Char] = Seq(setA, setB, setC, setD, setE, setF, setG)
            .zip(segments)
            .map(k => (k._1.head, k._2))
            .toMap

        entry.digits.map(scrambled => segmentsToDigit(scrambled.map(unscramble).sorted)).mkString.toInt
    }

}