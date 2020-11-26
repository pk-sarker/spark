# Create RDD
There is two ways to create RDD
* by loading data from external source
* parallelizing an existing collection in your driver program or distributing a collection of objects

Some of the common external data source:
* Files: JSON, CSV, sequence files, object files, Compressed formats
* Databases: Cassandra, HBase, Elasticsearch
* S3

It is possible to create RDD from Hive context.

Creating a RDD from text file
```scala
val sc = new SparkContext("local[*]", "HelloSpark")
val lines = sc.textFile("README.md")
```


Two things to do with RDD, **transformation** and **action**.
*Transformation* creates a new RDD from previous one.


## Transformation
* Map
* flatmap
* filter
* distinct
* sample
* union, intersection, subtract, cartesian
* 

#### Map
Map is a transformation operation in spark. It applies transformation on every element in the RDD, DataFrame and dataset and returns a new RDD with transformed data. 

Syntax:
```scala
def map[S](f: (T) ⇒ S)(implicit executor: ExecutionContext): Future[S]
```

Example:
```scala
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object MapTrans {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TransformationMap").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6))
    val incRdd = rdd1.map(element => element + 2)
    incRdd.collect().foreach(println)
  }
}

Output:
3
4
5
6
7
8
```

**Parallelize**: Parallelize method in `SparkContext` creates a parallelized collection which allowes Spark to distribute the data across multiple nodes, instead of depending on a single node to process the data.

```scala
def parallelize[T](seq: Seq[T], numSlices: Int = defaultParallelism)(implicit arg0: ClassTag[T]): RDD[T]

seq: Scala collection to distribute
numSlices: number of partitions to divide the collection into
returns RDD representing distributed collection
```
