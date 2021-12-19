package Day13

import scala.io.Source

object Day13 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day13/input")

        val input: Array[String] = source.getLines().toArray
        source.close()

        val (points, folds) = input.foldLeft((Set[(Int, Int)](), List[(Char, Int)]())) {
            case ((points, folds), instruction) => instruction match {
                case s if s.contains(",") =>
                    val pt = s.split(",")
                    (points + ((pt(0).toInt, pt(1).toInt)), folds)
                case s if s.contains("=") =>
                    val fold = s.split("=")
                    (points, (fold(0).last, fold(1).toInt) :: folds)
                case _ => (points, folds)
            }

        }
        println(part1(points, folds) + " (correct: 675)")
        part2(points, folds)
        println(" (correct: HZKHFEJZ)")
    }

    def part1(points: Set[(Int, Int)], folds: List[(Char, Int)]): Int = {
        fold(points, folds.reverse.head).size
    }

    def part2(points: Set[(Int, Int)], folds: List[(Char, Int)]): Unit = {
        printSolution(folds.reverse.foldLeft(points)(fold))
    }

    def fold(points: Set[(Int, Int)], how: (Char, Int)): Set[(Int, Int)] = {
        how match {
            case ('x', at)
            => points.map(p => (at - (at - p._1).abs, p._2))
            case ('y', at)
            => points.map(p => (p._1, at - (at - p._2).abs))
        }
    }

    def printSolution(points: Set[(Int, Int)]): Unit = {
        val (w, h) = (points.maxBy(_._1)._1, points.maxBy(_._2)._2)
        for (y <- 0 to h; x <- 0 to w) {
            print(if (points.contains((x, y))) "#" else " ")
            if (x == w) println()
        }
    }
}
