package consulting.starodubtsev.sparksamples

import org.apache.spark._
import org.apache.log4j._

object LineCounter {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "LineCounter")

    val lines = sc.textFile("data/alice_in_wonderland.txt")
    val numLines = lines.count()

    println("Here we go! " + numLines + " lines.")

    sc.stop()

  }

}
