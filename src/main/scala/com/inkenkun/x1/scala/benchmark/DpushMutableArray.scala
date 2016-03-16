package com.inkenkun.x1.scala.benchmark

import scala.collection.{mutable => mu }
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import java.util
import java.util.ArrayList

@State( Scope.Benchmark )
class DpushMutableArray {
  
  val   n1k = 10 * 10 * 10
  val  n10k = 10 * 10 * 10 * 10
  val n100k = 10 * 10 * 10 * 10 * 10

  import arrayImplicits._
  ArrayBufferMonoid
  ListBuffer

  @Benchmark
  def append$ArrayList$1k:   ArrayList[Int] = appendJavaCollection[ArrayList]( n1k )

  @Benchmark
  def append$ArrayList$10k:  ArrayList[Int] = appendJavaCollection[ArrayList]( n10k )

  @Benchmark
  def append$ArrayList$100k: ArrayList[Int] = appendJavaCollection[ArrayList]( n100k )

  @Benchmark
  def insert$ArrayList$1k:   ArrayList[Int] = insertJavaCollection[ArrayList]( n1k )

  @Benchmark
  def insert$ArrayList$10k:  ArrayList[Int] = insertJavaCollection[ArrayList]( n10k )

  @Benchmark
  def insert$ArrayList$100k: ArrayList[Int] = insertJavaCollection[ArrayList]( n100k )

  @Benchmark
  def appendLinkedList$1k:   ArrayList[Int] = appendJavaCollection[ArrayList]( n1k )

  @Benchmark
  def append$LinkedList$10k:  ArrayList[Int] = appendJavaCollection[ArrayList]( n10k )

  @Benchmark
  def append$LinkedList$100k: ArrayList[Int] = appendJavaCollection[ArrayList]( n100k )

  @Benchmark
  def insert$LinkedList$1k:   util.LinkedList[Int] = insertJavaCollection[util.LinkedList]( n1k )

  @Benchmark
  def insert$LinkedList$10k:  util.LinkedList[Int] = insertJavaCollection[util.LinkedList]( n10k )

  @Benchmark
  def insert$LinkedList$100k: util.LinkedList[Int] = insertJavaCollection[util.LinkedList]( n100k )

  @Benchmark
  def append$ArrayBuffer$1k:   ArrayBuffer[Int] = appendCollection[ArrayBuffer]( n1k )

  @Benchmark
  def append$ArrayBuffer$10k:  ArrayBuffer[Int] = appendCollection[ArrayBuffer]( n10k )

  @Benchmark
  def append$ArrayBuffer$100k: ArrayBuffer[Int] = appendCollection[ArrayBuffer]( n100k )

  @Benchmark
  def insert$ArrayBuffer$1k:   ArrayBuffer[Int] = insertCollection[ArrayBuffer]( n1k )

  @Benchmark
  def insert$ArrayBuffer$10k:  ArrayBuffer[Int] = insertCollection[ArrayBuffer]( n10k )

  @Benchmark
  def insert$ArrayBuffer$100k: ArrayBuffer[Int] = insertCollection[ArrayBuffer]( n100k )

  @Benchmark
  def append$ListBuffer$1k:    ListBuffer[Int] = appendCollection[ListBuffer]( n1k )

  @Benchmark
  def append$ListBuffer$10k:   ListBuffer[Int] = appendCollection[ListBuffer]( n10k )

  @Benchmark
  def append$ListBuffer$100k:  ListBuffer[Int] = appendCollection[ListBuffer]( n100k )

  @Benchmark
  def insert$ListBuffer$1k:    ListBuffer[Int] = insertCollection[ListBuffer]( n1k )

  @Benchmark
  def insert$ListBuffer$10k:   ListBuffer[Int] = insertCollection[ListBuffer]( n10k )

  @Benchmark
  def insert$ListBuffer$100k:  ListBuffer[Int] = insertCollection[ListBuffer]( n100k )


  private[benchmark] def appendCollection[M[_] <: mu.Buffer[_]](n: Int )(implicit m: Monoid[M, Int] ): M[Int] = {
    val xs = m.empty
    for ( i <- 1 to n ) {
      m.append( xs, i )
    }
    xs
  }

  private [benchmark] def appendJavaCollection[M[_] <: util.List[_]](n: Int)(implicit m: Monoid[M, Int]): M[Int] = {
    val xs = m.empty
    for(i <- 1 to n) {
      m.append(xs, n)
    }
    xs
  }

  private[benchmark] def insertCollection[M[__] <: mu.Buffer[_]](n: Int )(implicit m: Monoid[M, Int] ): M[Int] = {
    val xs = m.empty
    for ( i <- 1 to n ) {
      m.insert( xs, i )
    }
    xs
  }

  private [benchmark] def insertJavaCollection[M[_] <: util.List[_]](n: Int)(implicit m: Monoid[M, Int]): M[Int] = {
    val xs = m.empty
    for(i <- 1 to n) {
      m.insert(xs, n)
    }
    xs
  }
}
