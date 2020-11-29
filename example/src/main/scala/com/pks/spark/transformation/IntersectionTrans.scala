package com.pks.spark.transformation
import org.apache.spark.SparkContext

object IntersectionTrans {
  def main(args: Array[String]) : Unit = {
    val sparkContext = new SparkContext("local[*]", "IntersectionTrans")
    val rdd1 = sparkContext.parallelize(List(30,70,90,10))
    val rdd2 = sparkContext.parallelize(List(40,20,80,30,10))

    val rdd3 = rdd1.intersection(rdd2)
    rdd3.collect().foreach(element => {
      print(element + " ")
    })

    sparkContext.stop()
  }
}
