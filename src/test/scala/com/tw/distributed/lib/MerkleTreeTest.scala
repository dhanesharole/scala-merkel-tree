package com.tw.distributed.lib

class MerkleTreeTest extends org.scalatest.FunSuite {

  test("should be able to create Merkle tree from two datablocks") {

    val merkelTree = MerkleTree(List("dhanesh", "arole"))

    assert(merkelTree.rootHash == Hash("dhanesharole"))
  }

  test("should be able to create Merkle tree from from odd numbered datablocks") {
    val merkelTree = MerkleTree(List("dhanesh", "arole", "justin"))

    assert(merkelTree.rootHash == Hash("justindhanesharole"))
  }

  test("should be able to create Merkle tree for empty datablocks ") {
    val merkelTree = MerkleTree(List())

    assert(merkelTree.rootHash == Hash(""))
  }

}
