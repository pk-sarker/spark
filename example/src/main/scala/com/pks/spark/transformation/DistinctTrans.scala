package com.pks.spark.transformation

import org.apache.spark.SparkContext

object DistinctTrans {
  def main(args: Array[String]): Unit = {
    val sparkContext = new SparkContext("local[*]", "DistinctTrans")
    val rdd = sparkContext.parallelize(List(1,3,6,9,2,3,6,7,1))

    rdd.collect().foreach(num => {
      print(num + " ")
    })

    val distinctRdd = rdd.distinct()
    distinctRdd.collect().foreach(num => {
      print(num + " ")
    })
    sparkContext.stop()
  }
}
