package com.pks.spark.transformation
import org.apache.spark.SparkContext

object FilterTrans {
  def main(args: Array[String]): Unit = {
    val sparkContext = new SparkContext("local[*]", "FilterTrans")
    val rdd = sparkContext.parallelize(List(1,2,3,4,5,6,7,8,9))
    val even_rdd = rdd.filter(element => element%2 == 0)
    val odd_rdd = rdd.filter(element => element%2 == 1)
    println("Even numbers: ")
    even_rdd.collect().foreach(a => {
      print(a + " ")
    })

    println("\nOdd numbers: ")
    odd_rdd.collect().foreach(a => {
      print(a + " ")
    })
    sparkContext.stop()
  }
}
