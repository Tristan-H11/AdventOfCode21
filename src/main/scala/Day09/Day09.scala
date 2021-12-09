package Day09

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

object Day09 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day09/input")
        val values: ListBuffer[Vector[Int]] = new ListBuffer[Vector[Int]]()

        values.addAll(
            source.getLines().map(_.split("").map(_.toInt).toVector)
        )

        println(part1(values.toList) + " (correct: 530)")
        println(part2(values.toList) + " (correct: 1019494)")
    }

    def part1(xs: List[Vector[Int]]): Int = {
        (for ((i, j) <- lowestPoints(xs)) yield xs(i)(j) + 1).sum
    }

    def part2(xs: List[Vector[Int]]): Int = {
        val counts = for ((i, j) <- lowestPoints(xs)) yield countBasins(xs, i, j)
        counts.sorted
            .drop(counts.length - 3)
            .product
    }

    def lowestPoints(xs: List[Vector[Int]]): List[(Int, Int)] = {
        val lowestPoints: ListBuffer[(Int, Int)] = new ListBuffer[(Int, Int)]
        for (i <- xs.indices;
             j <- xs.head.indices) {
            breakable {
                val current = xs(i)(j)
                if (i > 0 && xs(i - 1)(j) <= current) break
                if (j > 0 && xs(i)(j - 1) <= current) break
                if (i < xs.length - 1 && xs(i + 1)(j) <= current) break
                if (j < xs.head.length - 1 && xs(i)(j + 1) <= current) break
                lowestPoints.addOne((i, j))
            }
        }
        lowestPoints.toList
    }

    def countBasins(xs: List[Vector[Int]], x: Int, y: Int): Int = {
        val queue = mutable.Stack((x, y))
        val visited: mutable.Set[(Int, Int)] = mutable.Set((x, y))

        while (queue.nonEmpty) {
            val (i, j) = queue.pop()
            for ((ii, jj) <- List((i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1))) {
                breakable {
                    if (ii < 0 || jj < 0 || ii >= xs.length || jj >= xs.head.length) break
                    if (visited.contains((ii, jj)) || xs(ii)(jj) == 9) break
                    if (xs(ii)(jj) >= xs(i)(j)) {
                        queue.append((ii, jj))
                        visited.add((ii, jj))
                    }
                }
            }
        }
        visited.size
    }
}
