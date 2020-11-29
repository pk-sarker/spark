package com.pks.spark.transformation
import org.apache.spark.SparkContext

object UnionTrans {
  def  main(args: Array[String]): Unit = {
    val sparkContext = new SparkContext("local[*]", "UnionTrans")
    val rdd_fruit1 = sparkContext.parallelize(List("Apple", "Orange", "Grape"))
    val rdd_fruit2 = sparkContext.parallelize(List("Cherry", "Blueberries", "Pear", "Strawberry", "Orange"))

    val rdd_fruit = rdd_fruit1.union(rdd_fruit2)

    rdd_fruit.collect().foreach(f => {
      print(f + " ")
    })
  }
}
