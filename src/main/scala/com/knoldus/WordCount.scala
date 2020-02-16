package com.knoldus

import java.io.FileWriter
import scala.io.Source
object WordCount {
  def count(fileName:String,outputDirectory:String):Map[String,Int]=
  {
    val fSource = Source.fromFile(fileName)
    val fWrite = new FileWriter(s"$outputDirectory/"+fileName.split("/").last)
    val resultMap:Map[String,Int]= fSource.getLines.flatMap(_.split("[\" \" \\.]")).foldLeft(Map.empty[String, Int]){(count, word) => count + (word.toLowerCase -> (count.getOrElse(word.toLowerCase, 0) + 1))}

    fWrite.write(resultMap.toString())
    fWrite.close()
    resultMap

  }
}
