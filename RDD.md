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

Another important thing is that `map()`'s return type doesn't have to be the same as its input type. Consider like there is a RDD that contains *String*, and  the  `map()` function parses the String and returns a *Double*.

**Parallelize**: Parallelize method in `SparkContext` creates a parallelized collection which allowes Spark to distribute the data across multiple nodes, instead of depending on a single node to process the data.

```scala
def parallelize[T](seq: Seq[T], numSlices: Int = defaultParallelism)(implicit arg0: ClassTag[T]): RDD[T]

seq: Scala collection to distribute
numSlices: number of partitions to divide the collection into
returns RDD representing distributed collection
```

#### Flatmap
Flatmap return a new RDD by first applying a function to all elements of the current RDD, and then flattening the results. For example, there may be some texts in a rdd and you want to create a new rdd where elements will be words in the text. Simplay you can pass a function to the flatmap, then it will execute that function on each element of the RDD and creates a new RDD.

```scala
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
```

#### Filter
Filter return a new RDD containing only the elements that satisfy a predicate. The `filter()` transformation takes in a function and returns an RDD that only has elements that pass the `filter()` function.
```scala
def filter(f: T => Boolean): RDD[T]
```
Lets have a example to filter odd and even number from a RDD
```scala
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

Even numbers: 
2 4 6 8
Odd numbers: 
1 3 5 7 9
```

#### distinct
Removes duplicate elements from RDD and returns a new RDD with unique elements.

```scala
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

1 3 6 9 2 3 6 7 1
1 2 3 6 7 9

```
#### sample

#### union
With union transformation it combines two RDDs and returns a new RDD. Union will keep the duplicated elements which consists in  both RDDs. 

```scala
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

Apple Orange Grape Cherry Blueberries Pear Strawberry Orange
```

#### Intersection
With `intersection()` transformation, it creates a new RDD by using the common elements in two RDDs and also removes duplicate element. *Union* and *Intersection* is similar concept but the performance of *Intersection* is worse than *Union* as it has to shuffle the elements over network.

```Scala
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
```


#### subtract
#### cartesian

## Collect results
There are some method which help to get the results back
* `collect()`: returns all the elements in RDD in a data structure/collection/array. This method should be only used when result is small, as all  data is loaded into the driver's memory.
* `count()`: Counts the number of elements in RDD.
* `countByValue()`: Counts each unique value in the RDD as a local map of (value, count) pairs.
* `take()`: Take the first num elements of the RDD. It works by first scanning one partition, and use the results from that partition to estimate the number of additional partitions needed to satisfy the limit.
* `reduce()`: Reduces the elements of this RDD using the specified commutative and  associative binary operator.
  

# Lazy evaluation
In the driver program nothing happens until an action is triggered. Basically, utill Spark understands what is  the ultimate goal that you want to achieve through the action, it doesn't know how to optimize the process.

