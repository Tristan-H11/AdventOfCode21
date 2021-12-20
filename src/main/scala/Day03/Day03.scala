package Day03

import scala.io.Source

object Day03 {

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day03/input")
        val input = source.getLines().toArray.map(e => e.split(""))
        source.close()
        println(part1(input) + " (correct: 2250414)")
        println(part2(input) + " (correct: 6085575)")
    }

    def part1(xs: Array[Array[String]]): Int = {
        val (high, low) = compareColumns(xs)
        Integer.valueOf(high, 2) * Integer.valueOf(low, 2)
    }

    def part2(xs: Array[Array[String]]): Int = {
        var filteredList1 = xs.clone()
        var result1: Array[String] = Array("")
        for (i <- xs.head.indices) {
            if (filteredList1.nonEmpty) {
                val transposedList = filteredList1.transpose
                val d = if (2 * transposedList(i).count(_ == "1") >= transposedList(i).length) "1" else "0"
                filteredList1 = filteredList1.filter(e => {
                    val digit = e(i)
                    digit == d
                })
                if (filteredList1.nonEmpty)
                    result1 = filteredList1.head
            }
        }

        var filteredList2 = xs.clone()
        var result2: Array[String] = Array("")

        for (i <- xs.head.indices) {
            if (filteredList2.nonEmpty) {
                val transposedList = filteredList2.transpose
                val d = if (2 * transposedList(i).count(_ == "1") >= transposedList(i).length) "0" else "1"
                filteredList2 = filteredList2.filter(e => {
                    val digit = e(i)
                    digit == d
                })
                if (filteredList2.nonEmpty)
                    result2 = filteredList2.head
            }
        }
        Integer.valueOf(result1.mkString, 2) * Integer.valueOf(result2.mkString, 2)
    }

    def compareColumns(xs: Array[Array[String]]): (String, String) = {
        val copy = xs.transpose

        val higherBuilder = new StringBuilder()
        val lowerBuilder = new StringBuilder()

        copy.foreach(e => {
            higherBuilder.append(if (e.count(_ == "1") * 2 >= e.length) 1 else 0)
            lowerBuilder.append(if (e.count(_ == "1") * 2 < e.length) 1 else 0)
        })

        (higherBuilder.toString(), lowerBuilder.toString())
    }
}
