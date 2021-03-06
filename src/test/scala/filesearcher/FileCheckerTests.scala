package filesearcher

import org.scalatest.FlatSpec
import java.io.File

class FileCheckerTests extends FlatSpec {
  "FilterChecker passed a list where one file matches the filter" should
  "return a list with that file" in {
    val listOfFiles = List(FileObject(new File("random")), FileObject(new File("match")))
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
    assert(matchedFiles == List(FileObject(new File("match"))))
  }

    "FilterChecker passed a list with a directory that matches the filter" should
    " should not return the directory" in {
      val listOfIOObjects = List(FileObject(new File("random")),
          DirectoryObject(new File("match")))
      val matchedFiles = FilterChecker("match") findMatchedFiles listOfIOObjects
      assert(matchedFiles.length == 0)
    }

    "FilterChecker passed a file with content that matches the filter 3 times" should
    "return 3" in {
      val isContentMatched = FilterChecker("Akinwale")
                                .findMatchedContentCount(new File("./testfiles/Akinwale.data"))
      assert(isContentMatched == 3)
    }

    "FilterChecker passed a file with content that does not match the filter" should
    "return 0" in {
      val isContentMatched = FilterChecker("Akinwale")
                                .findMatchedContentCount(new File("./testfiles/readme.txt"))
      assert(isContentMatched == 0)
    }

}
