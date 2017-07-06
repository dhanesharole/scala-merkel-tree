package com.tw.distributed.lib

sealed trait Node {
  def data: Hash
}

case class Leaf(data: Hash) extends Node

case class NonLeaf(data: Hash, left: Node, right: Option[Node]) extends Node