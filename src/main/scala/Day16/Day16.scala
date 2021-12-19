package Day16

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Day16 {

    trait Packet

    case class Literal(version: Long, value: Long) extends Packet

    case class Operator(version: Long, typeId: Long, packets: List[Packet]) extends Packet

    def main(args: Array[String]): Unit = {
        val source = Source.fromFile("src/main/scala/Day16/input")

        val input: String = hex2bin(source.mkString.trim)
        val packet: Packet = parse(input)._2.head

        println(part1(packet) + " (correct: 854)")
        println(part2(packet) + " (correct: 186189840660)")
    }

    def part1(packet: Packet): Long = versionSum(packet)

    def part2(packet: Packet): Long = expression(packet).get

    def versionSum(packet: Packet): Long = packet match
        case Literal(version, _) => version
        case Operator(version, _, packets) => version + packets.map(versionSum).sum

    def expression(packet: Packet): Option[Long] = {
        packet match
            case Literal(_, value) => Some(value)
            case Operator(_, typeId, packets) =>
                (typeId, packets.flatMap(expression)) match
                    case (0, xs) => Some(xs.sum)
                    case (1, xs) => Some(xs.product)
                    case (2, xs) => Some(xs.min)
                    case (3, xs) => Some(xs.max)
                    case (5, h :: i :: t) => Some(if h > i then 1 else 0)
                    case (6, h :: i :: t) => Some(if h < i then 1 else 0)
                    case (7, h :: i :: t) => Some(if h == i then 1 else 0)
                    case _ => None
    }

    def parse(xs: String, size: Int = 0, packets: List[Packet] = Nil): (Int, List[Packet]) = {
        val data = xs.drop(size)
        val version = bin2dec(data.take(3))
        val typeId = bin2dec(data.slice(3, 6))
        val (size2, packet2) =
            if typeId == 4 then parseLiteral(data, version)
            else parseOperator(data, version, typeId, data(6).asDigit)
        (size + size2, packets :+ packet2)
    }

    def parseOperator(xs: String, version: Long, typeId: Long, lengthId: Int): (Int, Packet) = {
        val (size, packets) = lengthId match
            case 1 =>
                val packetCount = bin2dec(xs.slice(7, 18)).toInt
                Iterator
                    .iterate((7 + 11, List.empty[Packet]))((sz, p) => parse(xs, sz, p))
                    .drop(packetCount)
                    .next()
            case _ =>
                val packetsSize = bin2dec(xs.slice(7, 22)) + 7 + 15
                Iterator
                    .iterate((7 + 15, List.empty[Packet]))((sz, p) => parse(xs, sz, p))
                    .dropWhile(_._1 < packetsSize)
                    .next()
        (size, Operator(version, typeId, packets))
    }

    def parseLiteral(xs: String, version: Long): (Int, Literal) = {
        val (blocks1, blocks0) = xs.drop(6).grouped(5).toList.span(_.head == '1')
        val value = (blocks1 :+ blocks0.head).map(_.tail).mkString
        val size = 6 + (blocks1.size + 1) * 5
        (size, Literal(version, bin2dec(value)))
    }

    def hex2bin(hex: Char): String = {
        val bin = Integer.parseInt(hex.toString, 16).toBinaryString
        leftPad(4, bin)
    }

    def hex2bin(hex: String): String = {
        hex.flatMap(hex2bin)
    }

    def leftPad(l: Int, s: String, c: Char = '0'): String = {
        List.fill(l - s.length)(c).mkString + s
    }

    def bin2dec(bin: String): Long = {
        BigInt(bin, 2).longValue
    }
}
