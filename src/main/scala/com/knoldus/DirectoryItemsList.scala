package com.knoldus

import java.io.File

object DirectoryItemsList {
  def directoryItems ( dir : List[File], listItems : List[String] ):List[String] = dir match {
    case Nil => listItems
    case head :: Nil => if(head.isFile) head.getPath :: listItems else directoryItems(head.listFiles.toList,listItems)
    case head :: rest => if(head.isFile) directoryItems(rest,head.getPath :: listItems) else directoryItems(dir.drop(1) ::: head.listFiles.toList , listItems)
  }
}
