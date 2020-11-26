package com.pks.spark.transformation

import org.apache.spark.sql.types.{IntegerType, StringType, StructType}
import org.apache.spark.sql.{Row, SparkSession}


object MapTransformDataFrames {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("MapTransformDataFrames").getOrCreate()

    val data = Seq(
      Row("Saha", "Mousumi", "", 923, 25, "Toronto"),
      Row("Sarker", "Pijus", "Kumar", 423, 45, "Toronto"),
      Row("Sarker", "Anik", "Kumar", 5609, 19, "Toronto"),
      Row("Sarker", "Joiotsna", "Rani", 12, 300, "Toronto")
    )
    val structureSchema = new StructType()
      .add("LastName", StringType)
      .add("FirstName", StringType)
      .add("MiddleName", StringType)
      .add("EmployeeID", IntegerType)
      .add("Experience", IntegerType)
      .add("City", StringType)

    val dataFrame = spark.createDataFrame(spark.sparkContext.parallelize(data),  structureSchema)
    dataFrame.printSchema()
    dataFrame.show(false)


    import spark.implicits._
    val dataFrame2 = dataFrame.map( row => {
      val name = row.getString(1) +" "+ row.getString(2) +" "+ row.getString(0)
      (name, row.getInt(3), row.getInt(4)/12)
    })

    val dataFrame3 = dataFrame2.toDF("FullName","ID","Duration(yr)")
    dataFrame3.printSchema()
    dataFrame3.show(false)
  }
}
