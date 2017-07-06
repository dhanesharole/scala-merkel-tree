package com.tw.distributed.lib

class MerkelTreeTest extends org.scalatest.FunSuite {

  test("should be able to create Merkel tree from two datablocks") {

    val merkelTree = MerkelTree(List("dhanesh", "arole"))

    assert(merkelTree.rootHash == Hash("dhanesharole"))
  }

  test("should be able to create Merkel tree from from odd numbered datablocks") {
    val merkelTree = MerkelTree(List("dhanesh", "arole", "justin"))

    assert(merkelTree.rootHash == Hash("justindhanesharole"))
  }

  test("should be able to create Merkel tree for empty datablocks ") {
    val merkelTree = MerkelTree(List())

    assert(merkelTree.rootHash == Hash(""))
  }

}
