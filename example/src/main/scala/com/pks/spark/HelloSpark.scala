package com.pks.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object HelloSpark {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sparkConf = new SparkConf().setAppName("HelloSpark").setMaster("local[*]")
    val sparkContext = new  SparkContext(sparkConf)
    // val sparkContext = new SparkContext("local[*]", "HelloSpark")

    val lines = sparkContext.textFile("data/paragraph.txt")

    val numLines = lines.count()

    println("The file has " + numLines + " lines.")
    sparkContext.stop()
  }
}
