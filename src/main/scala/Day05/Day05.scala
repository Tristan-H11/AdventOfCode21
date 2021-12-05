package Day05

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.math._

object Day05 {
    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day05/input")
        val input = source.getLines().toList
        source.close()

        val preparedList = prepareLineList(input)

        println(part1(preparedList) + " (correct: 5373)")
        println(part2(preparedList) + " (correct: 21514)")
    }

    def part1(xs: List[(Int, Int, Int, Int)]): Int = {
        calcIntersections(xs)._1
    }

    def part2(xs: List[(Int, Int, Int, Int)]): Int = {
        calcIntersections(xs)._2
    }

    /**
     * Calculates the amount if intersections on this list of lines.
     * First of tuple is just straight lines, while second is including also diaognal lines
     */
    def calcIntersections(lines: List[(Int, Int, Int, Int)]): (Int, Int) = {
        val straight: mutable.Map[(Int, Int), Int] = mutable.Map().withDefaultValue(0)
        val all: mutable.Map[(Int, Int), Int] = mutable.Map().withDefaultValue(0)

        val absAndSign = (a: Int, b: Int) => (abs(b - a), signum(b - a))

        for ((x1, y1, x2, y2) <- lines) {
            val (dx, sx) = absAndSign(x1, x2)
            val (dy, sy) = absAndSign(y1, y2)

            for (i <- 0 to max(dx, dy)) {
                //Steps, i*sign = step
                val x = x1 + i * sx
                val y = y1 + i * sy
                //straight line, bcs one of the diffs must be 0
                if (dx == 0 || dy == 0) {
                    straight((x, y)) += 1
                }
                all((x, y)) += 1
            }
        }
        (straight.values.count(_ > 1), all.values.count(_ > 1))
    }


    def prepareLineList(xs: List[String]): List[(Int, Int, Int, Int)] = {
        val points: ListBuffer[(Int, Int, Int, Int)] = new ListBuffer[(Int, Int, Int, Int)]()
        for (line <- xs) {
            points.addOne(
                (line.split(' ')(0).split(',')(0).toInt,
                 line.split(' ')(0).split(',')(1).toInt,
                 //Skip this arrow
                 line.split(' ')(2).split(',')(0).toInt,
                 line.split(' ')(2).split(',')(1).toInt)
            )
        }
        points.toList
    }

}
