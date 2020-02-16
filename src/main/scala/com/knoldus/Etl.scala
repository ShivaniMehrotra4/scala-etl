package com.knoldus

import java.io.{File, PrintWriter}

import scala.io.Source

object Etl extends App{



  val file_Object = new File("abc.txt" )
  val file_write = new PrintWriter("def.txt")
  val fSource = Source.fromFile(file_Object)
  /*while (fSource.hasNext)
  {
    print(fSource.next)
  }
*/

  for(lines <- fSource.getLines)
    {
      file_write.write(lines.toUpperCase)

    }

  // closing file
  fSource.close()
  file_write.close()

}
