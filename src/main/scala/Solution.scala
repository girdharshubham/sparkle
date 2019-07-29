import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object Solution {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession =
      Session.createSession("kafka-structured-streaming")

    import spark.implicits._

    val dataFrame: DataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "spark-test-run")
      .load()

    dataFrame.printSchema()

    val resultSet: Dataset[String] = dataFrame
      .selectExpr("CAST(value AS STRING)")
      .as[String]


    val query = resultSet.writeStream
      .outputMode("append")
      .format("console")
      .start()

    query.awaitTermination()

  }

}
