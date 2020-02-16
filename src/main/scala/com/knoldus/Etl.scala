package com.knoldus

import java.io.{File, PrintWriter}

import scala.io.Source

object Etl extends App {

  //PART-1
  val directoryPath = "/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/"
  val listOfFile: List[File] = FilesOfDirectories.readingDirectory(directoryPath)

  val listOfFilesInDirectories = DirectoryItemsList.directoryItems(listOfFile, List.empty[String])
  val resultingFiles: List[File] = ChoiceOfAction.readFromFiles(listOfFile)

  //Part-2
  val wordCount: Map[String, Int] = WordCount.count("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/WordCounts/k.txt", "/home/knoldus/Documents/Assignment-ETL Vidhisha/etl")
}
