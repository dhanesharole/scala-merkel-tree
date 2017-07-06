package com.tw.distributed.lib

class MerkleTreeTest extends org.scalatest.FunSuite {

  test("should be able to create Merkle tree from two datablocks") {

    val merkleTree = MerkleTree(List("dhanesh", "arole"))

    assert(merkleTree.rootHash == Hash("dhanesharole"))
  }

  test("should be able to create Merkle tree from from odd numbered datablocks") {
    val merkleTree = MerkleTree(List("dhanesh", "arole", "justin"))

    assert(merkleTree.rootHash == Hash("justindhanesharole"))
  }

  test("should be able to create Merkle tree for empty datablocks ") {
    val merkleTree = MerkleTree(List())

    assert(merkleTree.rootHash == Hash(""))
  }

}
