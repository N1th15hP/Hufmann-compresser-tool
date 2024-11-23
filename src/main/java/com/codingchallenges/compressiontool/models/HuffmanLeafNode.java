package com.codingchallenges.compressiontool.models;

public class HuffmanLeafNode implements HuffmanBaseNode {
	
	private int weight;
	private char element;
	
	public HuffmanLeafNode(int weight, char element) {
		this.weight = weight;
		this.element = element;
	}
	
	public char getElement() {
		return element;
	}

	@Override
	public int weight() {
		return weight;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public String toString() {
		return "HuffmanLeafNode [weight=" + weight + ", element=" + element + "]";
	}

	
}
