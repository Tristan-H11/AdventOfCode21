package Day10

import scala.collection.mutable
import scala.io.Source

object Day10 {

    val BIG_INT_ZERO = BigInt(0)
    val partOneScoringRubric = Map(')' -> BigInt(3), ']' -> BigInt(57), '}' -> BigInt(1197), '>' -> BigInt(25137))
    val partTwoScoringRubric = Map(')' -> BigInt(1), ']' -> BigInt(2), '}' -> BigInt(3), '>' -> BigInt(4))
    val operandOpposites = Map(
        ')' -> '(',
        ']' -> '[',
        '}' -> '{',
        '>' -> '<',
        '(' -> ')',
        '[' -> ']',
        '{' -> '}',
        '<' -> '>')

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day10/input")
        val input: Array[String] = source.getLines().toArray
        source.close()

        println(part1(input).toString + " (correct: 266301)")
        println(part2(input).toString + " (correct: 3404870164)")
    }

    def part1(lines: Array[String]): BigInt = {
        lines.map(partOneMappingFunc).sum
    }

    def part2(lines: Array[String]): BigInt = {
        val zippedPartOnePartTwoScores: Array[(BigInt, BigInt)] = lines.map(partOneMappingFunc) zip lines.map(partTwoMappingFunc)
        val sortedHighScores: Array[BigInt] = zippedPartOnePartTwoScores
            .filter(_._1.equals(BIG_INT_ZERO))
            .map(_._2)
            .sorted
        sortedHighScores(sortedHighScores.length / 2)
    }

    def partOneMappingFunc(line: String): BigInt = {
        val stack = mutable.Stack[Char]()
        line.foldLeft(BIG_INT_ZERO)((acc, char) => {
            if (acc.equals(BIG_INT_ZERO)) {
                char match {
                    case ')' | ']' | '}' | '>' =>
                        if (stack.pop() != operandOpposites(char)) {
                            partOneScoringRubric(char)
                        } else {
                            acc
                        }
                    case _ =>
                        stack.push(char)
                        acc
                }
            } else {
                acc
            }
        })
    }

    def partTwoMappingFunc(line: String): BigInt = {
        val newLine = line.foldLeft(List[Char]())((acc, char) => {
            char match {
                case ')' | ']' | '}' | '>' =>
                    acc.tail
                case char =>
                    char +: acc
            }
        })
        newLine.foldLeft(BIG_INT_ZERO)((acc, char) => {
            BigInt(5) * acc + partTwoScoringRubric(operandOpposites(char))
        })
    }
}
