package com.knoldus

import java.io.{File, FileNotFoundException}

object FilesOfDirectories {
  def readingDirectory(path: String ): List[File] = {
    val pathDir = new File(path)
    if(pathDir.exists && pathDir.isDirectory)
    {
      pathDir.listFiles.filter(_.isFile).toList
    }
    else
      throw new FileNotFoundException("No Directory Present")
  }
}
