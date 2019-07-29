import org.apache.spark.sql.SparkSession

object Session {
  def createSession(appName: String): SparkSession = SparkSession
    .builder
    .appName(appName)
    .getOrCreate()
}
