package com.codingchallenges.compressiontool.models;

public class HuffTree implements Comparable<Object> {

	private HuffmanBaseNode root;

	public HuffTree(int weight, char element) {
		root = new HuffmanLeafNode(weight, element);
	}

	public HuffTree(HuffmanBaseNode left, HuffmanBaseNode right, int weight) {
		root = new HuffmanInternalNode(weight, left, right);
	}
	
	public int weight () {
		return root.weight();
	}
	
	public HuffmanBaseNode root() {
		return root;
	}

	@Override
	public int compareTo(Object o) {
		HuffTree that = (HuffTree) o;
		if (root.weight() < that.weight()) {
			return -1;
		} else if (root.weight() == that.weight()) {
			return 0;
		} else {
			return 1;
		}
	}

	

}
