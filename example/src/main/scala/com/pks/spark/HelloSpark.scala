package com.pks.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object HelloSpark {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "HelloSpark")

    val lines = sc.textFile("data/paragraph.txt")

    val numLines = lines.count()

    println("The file has " + numLines + " lines.")

    sc.stop()
  }
}
