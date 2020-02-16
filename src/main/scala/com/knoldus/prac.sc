import java.io.{File, FileNotFoundException, PrintWriter}

import scala.io.Source

@scala.annotation.tailrec
def directoryItems(dir : List[File], listItems : List[String] ):List[String] = dir match {
  case Nil => listItems
  case head :: Nil => if(head.isFile) head.getPath :: listItems else directoryItems(head.listFiles.toList,listItems)
  case head :: rest => if(head.isFile) directoryItems(rest,head.getPath :: listItems) else directoryItems(dir.drop(1) ::: head.listFiles.toList , listItems)
}

def readingDirectory(path: String ): List[File] = {
  val pathDir = new File(path)
  if(pathDir.exists && pathDir.isDirectory)
  {
    pathDir.listFiles.filter(_.isFile).toList
  }
  else
    throw new FileNotFoundException("No Directory Present")

}
@scala.annotation.tailrec
def readFromFiles(listFiles : List[File]): List[File] = {
  //val newDir = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/DuplicateDirectory/")

  listFiles match {
    case Nil => listFiles
    case head :: Nil =>
      val fSource = Source.fromFile(head)
      val fname = head.getName
      val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/"+fname)
      println(newPath)
      val fw = new PrintWriter(new File(fname))
      for (lines <- fSource.getLines)
      {
        //println(lines)
        fw.write(lines.toUpperCase)
      }
      fSource.close()
      fw.close()
      readFromFiles(listFiles.drop(1))

      case head :: rest =>
        val fSource = Source.fromFile(head)
        val fname = head.getName
        val newPath = new File("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates/"+fname)
        val fw = new PrintWriter(new File(fname))
        for (lines <- fSource.getLines)
        {
          //println(lines)
          fw.write(lines.toUpperCase)

        }
        fw.close()
        fSource.close()

        readFromFiles(listFiles.drop(1))


  }


}

val lf : List[File] = readingDirectory("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl")
directoryItems(lf,List.empty[String])
val rf : List[File] = readFromFiles(lf)
val finalResult = readingDirectory("/home/knoldus/Documents/Assignment-ETL Vidhisha/etl/Duplicates")

