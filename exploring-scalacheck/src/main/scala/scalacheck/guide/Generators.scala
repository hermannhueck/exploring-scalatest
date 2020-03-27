package scalacheck.guide

import org.scalacheck.Gen

import scala.util.chaining._

object Generators extends util.App {

  val myGen: Gen[(Int, Int)] =
    for {
      n <- Gen.choose(10, 20)
      m <- Gen.choose(2 * n, 500)
    } yield (n, m)
  s"generated a pair of Int (using Gen.choose) ---> " pipe print
  myGen.sample pipe println

  val vowel = Gen.oneOf('A', 'E', 'I', 'O', 'U', 'Y')
  s"generated an uppercase vowel (using Gen.oneOf) ---> " pipe print
  vowel.sample pipe println

  val vowel2 = Gen.frequency(
    (3, 'A'),
    (4, 'E'),
    (2, 'I'),
    (3, 'O'),
    (1, 'U'),
    (1, 'Y')
  )
  s"generated an uppercase vowel (using Gen.frequency) ---> " pipe print
  vowel2.sample pipe println

  sealed abstract class Tree
  case object Leaf                                 extends Tree
  case class Node(left: Tree, right: Tree, v: Int) extends Tree

  import org.scalacheck.Arbitrary.arbitrary

  val genLeaf = Gen.const(Leaf)

  val genNode = for {
    v     <- arbitrary[Int]
    left  <- genTree
    right <- genTree
  } yield Node(left, right, v)

  def genTree: Gen[Tree] = Gen.oneOf(genLeaf, Gen.lzy(genNode))

  s"Generating Case Classes (e.g. a Tree) ---> " pipe print
  genTree.sample pipe println

  def matrix[T](g: Gen[T]): Gen[Seq[Seq[T]]] = Gen.sized { size =>
    @com.github.ghik.silencer.silent("deprecated")
    @com.github.ghik.silencer.silent("implicit numeric widening")
    val side = scala.math.sqrt(size).asInstanceOf[Int]
    Gen.listOfN(side, Gen.listOfN(side, g))
  }
  s"generated a matrix of alpha chars (using Gen.sized) ---> " pipe print
  matrix(Gen.alphaChar).sample pipe println

  val smallEvenInteger = Gen.choose(0, 200) suchThat (_ % 2 == 0)
  s"generated a small even Int (using Gen.suchThat) ---> " pipe print
  smallEvenInteger.sample pipe println

  val genIntList = Gen.containerOf[List, Int](Gen.oneOf(1, 3, 5))
  s"generated a List[Int] (using Gen.containerOf) ---> " pipe print
  genIntList.sample pipe println

  @com.github.ghik.silencer.silent("deprecated")
  val genStringLazyList = Gen.containerOf[Stream, String](Gen.alphaStr)
  s"generated a LazyList[String] (using Gen.containerOf) ---> " pipe print
  genStringLazyList.sample.map(_.take(10)) pipe println

  val genBoolArray = Gen.containerOf[Array, Boolean](true)
  s"generated a Array[Boolean] (using Gen.containerOf) ---> Array.toList: " pipe print
  genBoolArray.sample.get.toList pipe println

  val zeroOrMoreDigits = Gen.someOf(1 to 9)
  s"generated a Container of Int values (using Gen.someOf) ---> " pipe print
  zeroOrMoreDigits.sample pipe println

  val oneOrMoreDigits = Gen.atLeastOne(1 to 9)
  s"generated a Container of Int values (using Gen.atLeastOne) ---> " pipe print
  oneOrMoreDigits.sample pipe println

  val fiveDice = Gen.pick(5, 1 to 6)
  s"generated a Container of randomly picked 6 Int values (using Gen.pick) ---> " pipe print
  fiveDice.sample pipe println

  val threeLetters = Gen.pick(3, 'A' to 'Z')
  s"generated a Container of randomly picked 3 Char values (using Gen.pick) ---> " pipe print
  threeLetters.sample pipe println
  threeLetters.sample pipe println

  val threeLettersPermuted = threeLetters.map(scala.util.Random.shuffle(_))
  s"generated a Container of randomly picked 3 Char values, randomly shuffled (using Gen.pick) ---> " pipe print
  threeLetters.sample pipe println
  threeLetters.sample pipe println
}
