package com.tw.distributed

import com.tw.distributed.core.lib._

import scala.annotation.tailrec

class MerkelTree private(dataBlocks: List[String]) {

  private lazy val hashTree: Tree = mkHashTree(dataBlocks)

  def rootHash: Hash = hashTree.rootNode.data

  private def mkHashTree(dataBlocks: List[String]): Tree = {

    @tailrec
    def breadthWiseHashing(nextLevelNodes: List[Node], nodes: List[Node]): List[Node] = takeFirstTwo(nodes) match {
      case (None, _) => nextLevelNodes
      case (Some(left), None) =>
        breadthWiseHashing(NonLeaf(Hash(left.data.value), left, None)::nextLevelNodes, List.empty[Node])

      case (Some(left), Some(right)) =>
        val newNonLeafNode = NonLeaf(Hash(left.data.value ++ right.data.value), left, Some(right))
        breadthWiseHashing(newNonLeafNode::nextLevelNodes, nodes.tail.tail)
    }

    def mkHash(data: List[Node]) : Node = {
      val currentLevelHashList = breadthWiseHashing(List.empty[Node], data)
      currentLevelHashList match {
        case List() => Leaf(Hash(""))
        case List(rootNode) => rootNode
        case _ => breadthWiseHashing(currentLevelHashList, currentLevelHashList).head
      }
    }

    Tree(mkHash(dataBlocks.map(data => Leaf(Hash(data)))))
  }

  private def takeFirstTwo(nodes: List[Node]): (Option[Node], Option[Node]) = nodes match {
    case List() => (None, None)
    case List(ele) => (Some(ele), None)
    case h :: t => (Some(h), Some(t.head))
  }
}

object MerkelTree {
  def apply(dataBlocks: List[String]): MerkelTree = new MerkelTree(dataBlocks)
}