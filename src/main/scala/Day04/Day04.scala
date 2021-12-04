package Day04

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day04 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day04/input")
        val input = source.mkString
        source.close()

        val numberString: Array[String] = input.split("\r\n\r\n") //Damn windows needs to be split at CRLF instead of LF lol
        val bingoNumbers: List[Int] = numberString.head.split(",").map(_.toInt).toList
        val boards: Array[BingoBoard] = makeBoards(numberString.drop(1)) //Drop(1) bcs of the bingoNumbers


        println(part1(bingoNumbers, boards) + " (correct: 64084)")
        println(part2(bingoNumbers, boards) + " (correct: 12833)")
    }

    def part1(numbers: List[Int], boards: Array[BingoBoard]): Int = {
        calcWinningBoards(numbers, boards).head
    }

    def part2(numbers: List[Int], boards: Array[BingoBoard]): Int = {
        calcWinningBoards(numbers, boards).last
    }

    private def calcWinningBoards(numbers: List[Int], boards: Array[BingoBoard]): List[Int] = {

        val winningSums: ListBuffer[Int] = new ListBuffer[Int]

        numbers.foldLeft(boards)((currentBoard, currentNumber) => {
            val newBoards: Array[BingoBoard] = currentBoard.map(_.markAsChecked(currentNumber))
            val winner: Option[BingoBoard] = newBoards.find(_.hasWon)
            winner.foreach(winningBoard => {
                winningSums.addOne(winningBoard.xs.sum * currentNumber)
            })

            newBoards.filterNot(_.hasWon)
        })
        winningSums.toList
    }

    private def makeBoards(input: Array[String]): Array[BingoBoard] = {
        input.map(currentBoard => {
            BingoBoard(
                ("\\d+".r).findAllMatchIn(currentBoard) //Find all numbers
                .map(_.matched.toInt) //Map regex-matches to integer
                .toList
            )
        })
    }

    sealed case class BingoBoard(xs: List[Int]) {
        /**
         * Makes all matches numbers map to zero. This way for winning just the sum need to be zero
         * and the final sum is easier to calc.
         */
        def markAsChecked(num: Int): BingoBoard = {
            BingoBoard(xs.map(i => if (num == i) 0 else i))
        }

        def hasWon: Boolean = {
            xs.grouped(5).exists(_.sum == 0) || //all horizontal, adjacent permutations
                (xs(0) + xs(5) + xs(10) + xs(15) + xs(20)) == 0 || //vertical 1
                (xs(1) + xs(6) + xs(11) + xs(16) + xs(21)) == 0 || //vertical 2
                (xs(2) + xs(7) + xs(12) + xs(17) + xs(22)) == 0 || //vertical 3
                (xs(3) + xs(8) + xs(13) + xs(18) + xs(23)) == 0 || //vertical 4
                (xs(4) + xs(9) + xs(14) + xs(19) + xs(24)) == 0    //vertical 5
        }
    }


}
