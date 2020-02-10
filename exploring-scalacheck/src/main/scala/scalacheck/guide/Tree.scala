package scalacheck.guide

sealed abstract class Tree[T] {

  def merge(t: Tree[T]) = Internal(List(this, t))

  def size: Int = this match {
    case Leaf(_)            => 1
    case Internal(children) => children.foldRight(0)(_.size + _)
  }
}

case class Internal[T](children: Seq[Tree[T]]) extends Tree[T]

case class Leaf[T](elem: T) extends Tree[T]

import org.scalacheck.{Arbitrary, Gen, Prop}
import Prop.forAll

object TreeCheck extends util.App {

  implicit def arbTree[T](implicit a: Arbitrary[T]): Arbitrary[Tree[T]] =
    Arbitrary {
      val genLeaf = for (e <- Arbitrary.arbitrary[T]) yield Leaf(e)

      def genInternal(sz: Int): Gen[Tree[T]] =
        for {
          n <- Gen.choose(sz / 3, sz / 2)
          c <- Gen.listOfN(n, sizedTree(sz / 2))
        } yield Internal(c)

      def sizedTree(sz: Int) =
        if (sz <= 0) genLeaf
        else Gen.frequency((1, genLeaf), (3, genInternal(sz)))

      Gen.sized(sz => sizedTree(sz))
    }

  val propMergeTree = forAll { (t1: Tree[Int], t2: Tree[Int]) =>
    t1.size + t2.size == t1.merge(t2).size
  }
  propMergeTree.check
}
