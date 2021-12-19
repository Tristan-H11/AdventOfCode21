package Day17

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Using

object Day17 {

    private val targetArea = "^target area: x=(\\d+)..(\\d+), y=(-?\\d+)..(-?\\d+)".r
    private var xRange = Range.inclusive(0, 0)
    private var yRange = Range.inclusive(0, 0)
    //I have no clue, how to initialize those ranges lol

    def main(args: Array[String]): Unit = {
        val targetArea(xMin, xMax, yMin, yMax) = Using.resource(Source.fromResource("Day17/input"))(_.mkString.trim)

        xRange = xMin.toInt to xMax.toInt
        yRange = yMin.toInt to yMax.toInt

        println(part1 + " (correct: 5778)")
        println(part2 + " (correct: 2576)")
    }

    def part1: Int = {
        yRange.min * (yRange.min + 1) / 2
    }

    def part2: Int = {
        val maxHeights: Seq[Int] = {
            for {
                xVelocity <- 0 to 1000
                yVelocity <- yRange.min to 1000
            } yield maxHeight(xVelocity, yVelocity)
        }
        maxHeights.count(_ != Int.MinValue)
    }

    @tailrec
    def maxHeight(xVelocity: Int,
                  yVelocity: Int,
                  xPosition: Int = 0,
                  yPosition: Int = 0,
                  seenHeights: Set[Int] = Set()
                 ): Int = {
        if (xVelocity <= 0 && !xRange.contains(xPosition)) { // Didn't make it far enough, or overshot
            Int.MinValue
        } else if (yPosition < yRange.min) { // Went too low
            Int.MinValue
        } else if (xRange.contains(xPosition) && yRange.contains(yPosition)) { // Hit the target
            seenHeights.max
        } else { // Still going up or still coming down
            val newXPosition = xPosition + xVelocity
            val newYPosition = yPosition + yVelocity
            val newXVelocity = (xVelocity - 1).max(0)
            val newYVelocity = yVelocity - 1
            maxHeight(newXVelocity, newYVelocity, newXPosition, newYPosition, seenHeights + newYPosition)
        }
    }
}
