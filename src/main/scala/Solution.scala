import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object Solution {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Solution")
      .getOrCreate()

    import spark.implicits._


    val df: DataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "spark-test-run")
      .load()

    df.printSchema()

    val result: Dataset[String] = df
      .selectExpr("CAST(value AS STRING)")
      .as[String]


    val query = result.writeStream
      .outputMode("append")
      .format("console")
      .start()

    query.awaitTermination()

  }

}
