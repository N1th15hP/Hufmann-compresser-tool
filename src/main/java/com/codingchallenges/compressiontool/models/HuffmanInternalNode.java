package com.codingchallenges.compressiontool.models;

public class HuffmanInternalNode implements HuffmanBaseNode {
	private int weight;
	private HuffmanBaseNode left;
	private HuffmanBaseNode right;
	
	public HuffmanInternalNode(int weight, HuffmanBaseNode left, HuffmanBaseNode right) {
		super();
		this.weight = weight;
		this.left = left;
		this.right = right;
	}
	
	public HuffmanBaseNode getLeft() {
		return left;
	}
	public void setLeft(HuffmanBaseNode left) {
		this.left = left;
	}
	public HuffmanBaseNode getRight() {
		return right;
	}
	public void setRight(HuffmanBaseNode right) {
		this.right = right;
	}
	@Override
	public int weight() {
		return weight;
	}
	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public String toString() {
		return "HuffmanInternalNode [weight=" + weight + ", left=" + left + ", right=" + right + "]";
	}


	
	
}
