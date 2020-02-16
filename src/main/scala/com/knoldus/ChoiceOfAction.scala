package com.knoldus

import java.io.{File, FileWriter}

import scala.io.{BufferedSource, Source}

object ChoiceOfAction {

  @scala.annotation.tailrec
  def readFromFiles(listFiles: List[File]): List[File] = {

    listFiles match {

      case Nil => listFiles

      case head :: Nil =>
        val fSource = Source.fromFile(head)
        val fName = head.getName
        val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/" + fName)
        writeToFiles(fSource, newPath)
        readFromFiles(listFiles.drop(1))

      case head :: rest =>
        val fSource = Source.fromFile(head)
        val fName = head.getName
        val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/" + fName)
        writeToFiles(fSource, newPath)
        readFromFiles(listFiles.drop(1))
    }
  }

  def writeToFiles(fSource: BufferedSource, newPath: File): Unit = {
    val fw = new FileWriter(newPath)
    for (lines <- fSource.getLines) {
      fw.write(lines.toUpperCase + "\n")
    }
    fw.close()
    fSource.close()

  }
}
