package com.knoldus

import java.io.{File, FileNotFoundException, PrintWriter}

import scala.io.Source

class ReadingDirectory {

  def readingDirectory(path: String ): List[File] = {
    val pathDir = new File(path)
    if(pathDir.exists && pathDir.isDirectory)
      {
        pathDir.listFiles.filter(_.isFile).toList
      }
    else
      throw new FileNotFoundException("No Directory Present")

  }

  def directoryItems ( dir : List[File], listItems : List[String] ):List[String] = dir match {
    case Nil => listItems
    case head :: Nil => if(head.isFile) head.getPath :: listItems else directoryItems(head.listFiles.toList,listItems)
    case head :: rest => if(head.isFile) directoryItems(rest,head.getPath :: listItems) else directoryItems(dir.drop(1) ::: head.listFiles.toList , listItems)
  }

  def readFromFiles ( listFiles : List[String]): Unit =
  {
    val newDir = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/DuplicateDirectory")
    val file_write = new PrintWriter("def.txt")
    listFiles match {
      case Nil => listFiles
      case head :: Nil =>
        val fSource = Source.fromFile(head)
        val file_write = new PrintWriter("newDir"+head)
        for(lines <- fSource.getLines)
        {
          file_write.write(lines.toUpperCase)

        }
    }
  }

}
