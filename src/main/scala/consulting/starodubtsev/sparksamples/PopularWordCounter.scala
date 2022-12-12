package consulting.starodubtsev.sparksamples

import org.apache.spark._
import org.apache.log4j._
import org.apache.spark.rdd.RDD

object PopularWordCounter {

  def main(arg:Array[String]) = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "WordCounter")

    val linesRdd = sc.textFile("data/alice_in_wonderland.txt")

    val wordsRdd: RDD[String] = linesRdd.flatMap(line => line.split("\\W+"))
                                  .map(line => line.toLowerCase());

    println(s"Words: ${wordsRdd.count()}")

    val countedWords:RDD[(Int, String)] = wordsRdd
                                          .map(w => (w, 1))
                                          .reduceByKey( (count1,count2) => count1 + count2) // counting the occurrences of teach word
                                          .map(x => (x._2, x._1))              // flipping {count, word}

    val sortedWordsRdd = countedWords.sortByKey(ascending = false, numPartitions = 1)  // by the count

    val top100 =  sortedWordsRdd.take(100)

    for (result <- top100) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }

    /*
       Somehow I thought that word "depends" would be the one of the most often used word in the
        Alice-in-the-wonder-land book: yet it is encountered only once.

        That phrase " `That depends a good deal on where you want to get to,' said the Cat."
        is one of the the famous one, I guess in this book. and yet, only one "depends". so to be famous.. it does not mean to be repetitive )
     */

    sc.stop()
  }
}
