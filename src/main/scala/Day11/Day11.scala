package Day11

import scala.collection.mutable
import scala.io.Source.fromInputStream

object Day11 {

    val LENGTH = 10

    sealed trait ProcessCoordinateAction

    case object IncreaseEnergy extends ProcessCoordinateAction

    case object Flash extends ProcessCoordinateAction

    def main(args: Array[String]): Unit = {
        val source = fromInputStream(ClassLoader.getSystemResourceAsStream("Day11/example"))
        val lines = source.getLines().toArray
            .map(_.trim.split("")
                .map(_.toInt))
        source.close()

        println(part1(lines) + " (correct: 1697)")
        println(part2(lines) + " (correct: 344)")
    }

    def part1(grid: Array[Array[Int]]): Int = {
        val flashedSet = mutable.HashSet[(Int, Int)]()
        var flashesCount = 0

        (1 to 100).foreach(_ => {
            increaseGridEnergy(grid, flashedSet)

            flashesCount = flashesCount + flashedSet.size
            setFlashedToZero(grid, flashedSet.toList)
            flashedSet.clear()
        })
        flashesCount
    }


    /*
     * For some reason, this thing is sometimes off by exact 100...
     */
    def part2(grid: Array[Array[Int]]): Int = {
        val flashedSet = mutable.HashSet[(Int, Int)]()
        var step = 0

        while (flashedSet.size != 100) {
            flashedSet.clear()

            increaseGridEnergy(grid, flashedSet)

            setFlashedToZero(grid, flashedSet.toList)
            step += 1
        }
        step
    }

    private def increaseGridEnergy(grid: Array[Array[Int]], flashingSet: mutable.HashSet[(Int, Int)]): Unit = {
        (0 until LENGTH).foreach(row => {
            (0 until LENGTH).foreach(col => {
                processCoordinate(IncreaseEnergy, grid, flashingSet, row, col)
            })
        })
    }

    def processCoordinate(action: ProcessCoordinateAction,
                          grid: Array[Array[Int]],
                          flashingSet: mutable.HashSet[(Int, Int)],
                          row: Int,
                          col: Int): Unit = {
        action match {
            case IncreaseEnergy =>
                val newEnergyLevel = grid(row)(col) + 1
                grid(row)(col) = newEnergyLevel

                if (!flashingSet.contains((row, col)) && newEnergyLevel > 9) {
                    processCoordinate(Flash, grid, flashingSet, row, col)
                }
            case Flash =>
                flashingSet.add((row, col))
                // Upper-Left
                if (row - 1 >= 0 && col - 1 >= 0) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row - 1, col - 1)
                }
                // Top
                if (row - 1 >= 0) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row - 1, col)
                }
                // Upper-Right
                if (row - 1 >= 0 && col + 1 < LENGTH) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row - 1, col + 1)
                }
                // Left
                if (col - 1 >= 0) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row, col - 1)
                }
                // Right
                if (col + 1 < LENGTH) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row, col + 1)
                }
                // Lower-Left
                if (row + 1 < LENGTH && col - 1 >= 0) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row + 1, col - 1)
                }
                // Bottom
                if (row + 1 < LENGTH) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row + 1, col)
                }
                // Lower-Right
                if (row + 1 < LENGTH && col + 1 < LENGTH) {
                    processCoordinate(IncreaseEnergy, grid, flashingSet, row + 1, col + 1)
                }
        }
    }

    def setFlashedToZero(grid: Array[Array[Int]],
                         flashCords: List[(Int, Int)]): Unit = {
        flashCords.foreach {
            case (row, col) =>
                grid(row)(col) = 0
        }
    }
}
