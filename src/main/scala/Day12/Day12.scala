package Day12

import scala.collection.mutable
import scala.io.Source

object Day12 {

    val adj: mutable.Map[String, Vector[String]] = mutable.Map[String, Vector[String]]().withDefaultValue(Vector[String]())

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day12/example")

        source.getLines().foreach(e => {
            val c = e.split("-")
            adj.update(c(0), adj(c(0)).appended(c(1)))
            adj.update(c(1), adj(c(1)).appended(c(0)))
        })

        println(part1 + " (correct: 3887)")
        println(part2 + " (correct: 104834)")
    }

    def part1: Int = {
        bfs("start", Set("start"), part2 = false)
    }

    def part2: Int = {
        bfs("start", Set("start"), part2 = true)
    }

    def bfs(currentCave: String, seen: Set[String], part2: Boolean): Int = {
        if(currentCave == "end") return 1
        var count = 0
        for(cave <- adj(currentCave)){
            if(!seen.contains(cave)){
                if(cave.toLowerCase == cave) {
                    count += bfs(cave, seen + cave, part2)
                } else {
                    count += bfs(cave, seen, part2)
                }
            } else if(cave != "start" && part2){
                count += bfs(cave, seen, part2 = false)
            }
        }
        count
    }
}
