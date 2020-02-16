package com.knoldus

import java.io.{File, FileNotFoundException, FileWriter}

import scala.io.Source

class ReadingDirectory {

  def readingDirectory(path: String): List[File] = {
    val pathDir = new File(path)
    if (pathDir.exists && pathDir.isDirectory) pathDir.listFiles.filter(_.isFile).toList else throw new FileNotFoundException("No Directory Present")
  }

  def directoryItems(dir: List[File], listItems: List[String]): List[String] = dir match {
    case Nil => listItems
    case head :: Nil => if (head.isFile) head.getPath :: listItems else directoryItems(head.listFiles.toList, listItems)
    case head :: rest => if (head.isFile) directoryItems(rest, head.getPath :: listItems) else directoryItems(dir.drop(1) ::: head.listFiles.toList, listItems)
  }


  def readFromFiles(listFiles: List[File]): List[File] = {
    //val newDir = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/DuplicateDirectory/")

    listFiles match {

      case Nil => listFiles

      case head :: Nil =>
        val fSource = Source.fromFile(head)
        val fName = head.getName
        val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/" + fName)

        val fw = new FileWriter(newPath)
        for (lines <- fSource.getLines) {

          fw.write(lines.toUpperCase)
        }
        fSource.close()
        fw.close()
        readFromFiles(listFiles.drop(1))

      case head :: rest =>
        val fSource = Source.fromFile(head)
        val fName = head.getName
        val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/" + fName)
        val fw = new FileWriter(newPath)
        for (lines <- fSource.getLines) {

          fw.write(lines.toUpperCase)

        }
        fw.close()
        fSource.close()

        readFromFiles(listFiles.drop(1))


    }


  }

  def wordCount(fileName: String, outputDirectory: String): Map[String, Int] = {
    val fSource = Source.fromFile(fileName)
    val fWrite = new FileWriter(s"$outputDirectory/" + fileName.split("/").last)
    val resultMap: Map[String, Int] = fSource.getLines.flatMap(_.split("[\" \" ]"))
      .foldLeft(Map.empty[String, Int]) { (count, word) => count + (word.toLowerCase -> (count.getOrElse(word.toLowerCase, 0) + 1)) }

    fWrite.write(resultMap.toString())
    fWrite.close()
    resultMap

  }
}
