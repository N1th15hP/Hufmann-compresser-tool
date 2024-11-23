package com.codingchallenges.compressiontool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codingchallenges.compressiontool.models.HuffTree;
import com.codingchallenges.compressiontool.models.HuffmanBaseNode;
import com.codingchallenges.compressiontool.models.HuffmanInternalNode;
import com.codingchallenges.compressiontool.models.HuffmanLeafNode;

@Service
public class HuffmanEncoder {

	public Map<Character,String>  generateHuffmanCode(HuffTree tree){
		Map<Character, String> huffmanCode = new HashMap<>();
		generate(tree.root(), "", huffmanCode);
		return huffmanCode;
	}
	
	public List<String> encode(Map<Character,String> huffmanCode, List<String> content) {
		List<String> encodedContent = new ArrayList<>();
		
		for(String line : content) {
			StringBuilder builder = new StringBuilder();
			
			for(char ch : line.toCharArray()) {
				String code = huffmanCode.getOrDefault(ch,"");
				builder.append(code);
			}
			
			encodedContent.add(builder.toString());
		}
		
		return encodedContent;
	}

	private void generate(HuffmanBaseNode node, String code, Map<Character, String> huffmanCode) {
		
		if(node == null) {
			return;
		}
		
		if(node.isLeaf()) {
			HuffmanLeafNode leaf = (HuffmanLeafNode) node;
			huffmanCode.put(leaf.getElement(), code);
		} else {
			HuffmanInternalNode internalNode = (HuffmanInternalNode) node;
			generate(internalNode.getLeft(), code+"0", huffmanCode);
			generate(internalNode.getRight(), code+"1", huffmanCode);
		}
	}
}
