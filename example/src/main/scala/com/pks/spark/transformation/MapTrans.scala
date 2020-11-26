package com.pks.spark.transformation

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object MapTrans {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TransformationMap").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6))
    val incRdd = rdd1.map(element => element + 2)
    incRdd.collect().foreach( i => {
      print(i + " ")
    })
    // incRdd.collect().foreach(println)
  }
}
