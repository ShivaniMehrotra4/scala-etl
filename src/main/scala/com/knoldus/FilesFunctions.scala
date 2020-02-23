package com.knoldus

import java.io.File

trait FilesFunctions {

  def readFromFiles(listFiles: List[File]): List[File]

  def count(fileName: String, outputDirectory: String): Map[String, Int]

  def directoryItems ( dir : List[File], listItems : List[String] ):List[String]

  def readingDirectory(path: String ): List[File]
}
