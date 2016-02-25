package filesearcher

import org.scalatest.FlatSpec
import java.io.File


class MatcherTests extends FlatSpec {
  val testFilesRootPath = "C:\\users\\Akinwale\\Documents\\Demo\\FSearcher\\testfiles\\"
  "Matcher that is passed a file matching the filter" should
  "return a list with the file name" in {
    val matcher = new Matcher("fake", "fakePath")

    val results = matcher.execute()

    assert(results == List(("C:\\users\\Akinwale\\Documents\\Demo\\FSearcher\\fakePath", None)))
  }

  "Matcher using a directory containing one file matching the filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath())

    val results = matcher.execute()

    assert(results == List(("s{testFilesRootPath}readme.txt", None)))
  }

  "Matcher that is not passed a root file location" should
  "use the current location" in {
    val matcher = new Matcher("filter")
    assert(matcher.rootLocation == new File(".").getCanonicalPath())
  }

  "Matcher with sub folder matching a root location with two subtree files matching" should
  "return a list with those file names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File(".\\testfiles\\").getCanonicalPath(), searchSubDirectories)

    val results = matcher.execute()

    assert(results == List(("s{testFilesRootPath}notes.txt", None), ("s{testFilesRootPath}readme.txt", None)))
  }

//  "FilterChecker passed a file with content that matches the filter" should
//  "return that the match succeeded" in {
//    val isContentMatched = FilterChecker("Akinwale")
//    .findMatchedContentCount(new File("./testfiles/Akinwale.data"))
//
//        assert(isContentMatched == true)
//  }
//
//  "Filter passed a file with content that does not match the filter" should
//  "return that the match failed" in {
//    val isContentMatched = FilterChecker("Akinwale")
//    .findMatchedContentCount(new File("./testfiles/readme.txt"))
//
//    assert(isContentMatched == false)
//  }

  "Matcher given a path that has one file that matches the file filter and content filter" should
  "return a list with that file name" in {
    val matcher = new Matcher("data", new File(".").getCanonicalPath(), true,
        Some("Akinwale"))

    val matchedFiles = matcher.execute()

    assert(matchedFiles == List(("s{testFilesRootPath}Akinwale.data", Some(3))))

  }

  "Matcher given a file that has no file that matches the file filter and content filter" should
  "return an empy list" in {
    val matcher = new Matcher("txt", new File(".").getCanonicalPath(), true,
        Some("Akinwale"))

    val matchedFiles = matcher.execute()

    assert(matchedFiles == List())
  }
}
