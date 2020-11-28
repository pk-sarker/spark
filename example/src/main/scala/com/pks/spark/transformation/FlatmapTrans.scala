package com.pks.spark.transformation

import org.apache.spark.SparkContext

object FlatmapTrans {
  def main(args: Array[String]): Unit = {
    val sparkContext = new SparkContext("local[*]", "FlatmapTrans")
    val data = Seq("Project Gutenberg’s",
      "Alice’s Adventures in Wonderland")
    val rdd = sparkContext.parallelize(data)

    val new_rdd = rdd.flatMap(el => el.split(" "))
    new_rdd.collect().foreach( i => {
      println(i)
    })
  }
}
