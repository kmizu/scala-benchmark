package com.inkenkun.x1.scala.benchmark

import java.util
import java.util.ArrayList

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
object arrayImplicits {

  abstract class Monoid[M[_], X] {
    def empty: M[X]
    def append(rs: M[X], a: X ): M[X]
    def insert(rs: M[X], a: X ): M[X]
    def removeHead( rs: M[X] ): M[X]
    def removeTail( rs: M[X] ): M[X]
    def take( rs: M[X] ): M[X]
  }

  implicit object ListMonoid extends Monoid[({type F[X] = List[X]})#F, Int] {
    def empty: List[Int] = List.empty[Int]
    def append(rs: List[Int], a: Int ): List[Int] = rs :+ a
    def insert(rs: List[Int], a: Int ): List[Int] = a :: rs
    def removeHead( rs: List[Int] ): List[Int] = rs.tail
    def removeTail( rs: List[Int] ): List[Int] = rs.dropRight(1)
    def take( rs: List[Int] ): List[Int] = rs.take( rs.size - 1 )
  }
  implicit object VectorMonoid extends Monoid[({type F[X] = Vector[X]})#F, Int] {
    def empty: Vector[Int] = Vector.empty[Int]
    def append(rs: Vector[Int], a: Int ): Vector[Int] = rs :+ a
    def insert(rs: Vector[Int], a: Int ): Vector[Int] = a +: rs
    def removeHead( rs: Vector[Int] ): Vector[Int] = rs.tail
    def removeTail( rs: Vector[Int] ): Vector[Int] = rs.dropRight(1)
    def take( rs: Vector[Int] ): Vector[Int] = rs.take( rs.size - 1 )
  }
  implicit object StreamMonoid extends Monoid[({type F[X] = Stream[X]})#F, Int] {
    def empty: Stream[Int] = Stream.empty[Int]
    def append(rs: Stream[Int], a: Int ): Stream[Int] = rs :+ a
    def insert(rs: Stream[Int], a: Int ): Stream[Int] = a +: rs
    def removeHead( rs: Stream[Int] ): Stream[Int] = rs.tail
    def removeTail( rs: Stream[Int] ): Stream[Int] = rs.dropRight(1)
    def take( rs: Stream[Int] ): Stream[Int] = rs.take( rs.size - 1 )
  }
  implicit object ListBufferMonoid extends Monoid[({type F[X] = ListBuffer[X]})#F, Int] {
    def empty: ListBuffer[Int] = ListBuffer.empty[Int]
    def append(rs: ListBuffer[Int], a: Int ): ListBuffer[Int] = rs += a
    def insert(rs: ListBuffer[Int], a: Int ): ListBuffer[Int] = a +=: rs
    def removeHead( rs: ListBuffer[Int] ): ListBuffer[Int] = rs.drop(0)
    def removeTail( rs: ListBuffer[Int] ): ListBuffer[Int] = rs.dropRight(1)
    def take( rs: ListBuffer[Int] ): ListBuffer[Int] = rs.take( rs.size - 1 )
  }
  implicit object ArrayBufferMonoid extends Monoid[({type F[X] = ArrayBuffer[X]})#F, Int] {
    def empty: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
    def append(rs: ArrayBuffer[Int], a: Int ): ArrayBuffer[Int] = rs += a
    def insert(rs: ArrayBuffer[Int], a: Int ): ArrayBuffer[Int] = a +=: rs
    def removeHead( rs: ArrayBuffer[Int] ): ArrayBuffer[Int] = rs.tail
    def removeTail( rs: ArrayBuffer[Int] ): ArrayBuffer[Int] = rs.dropRight(1)
    def take( rs: ArrayBuffer[Int] ): ArrayBuffer[Int] = rs.take( rs.size - 1 )
  }
  implicit object ArrayListMonoid extends Monoid[({type F[X] = ArrayList[X]})#F, Int] {
    def empty: ArrayList[Int] = new ArrayList(100)
    def append(rs: ArrayList[Int], a: Int ): ArrayList[Int] = { rs.add(a); rs}
    def insert(rs: ArrayList[Int], a: Int ): ArrayList[Int] = { rs.add(0, a); rs }
    def removeHead( rs: ArrayList[Int] ): ArrayList[Int] = { rs.remove(0); rs }
    def removeTail( rs: ArrayList[Int] ): ArrayList[Int] = { rs.remove(rs.size() - 1); rs}
    def take( rs: ArrayList[Int] ): ArrayList[Int] = { rs.subList(0, rs.size - 1 ); rs }
  }
  implicit object LinkedListMonoid extends Monoid[({type F[X] = util.LinkedList[X]})#F, Int] {
    def empty: util.LinkedList[Int] = new util.LinkedList
    def append(rs: util.LinkedList[Int], a: Int ): util.LinkedList[Int] = { rs.add(a); rs}
    def insert(rs: util.LinkedList[Int], a: Int ): util.LinkedList[Int] = { rs.addFirst(a); rs}
    def removeHead( rs: util.LinkedList[Int] ): util.LinkedList[Int] = { rs.removeFirst(); rs }
    def removeTail( rs: util.LinkedList[Int] ): util.LinkedList[Int] = { rs.removeLast(); rs }
    def take( rs: util.LinkedList[Int] ): util.LinkedList[Int] = { rs.subList(0, rs.size - 1 ); rs }
  }
}
