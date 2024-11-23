package com.codingchallenges.compressiontool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.codingchallenges.compressiontool.models.HuffTree;

@Service
public class CompressionService {

	public Map<Character, Integer> getCharacterFrequencies(List<String> lines) {
		TreeMap<Character, Integer> frequencyMap = new TreeMap<>();

		for (String line : lines) {

			for (char ch : line.toCharArray()) {
				frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
			}
		}

		return frequencyMap;
	}

	public HuffTree buildTree(Map<Character, Integer> frequencyMap) {

		PriorityQueue<HuffTree> minHeap = new PriorityQueue<>();

		for (char element : frequencyMap.keySet()) {
			HuffTree node = new HuffTree(frequencyMap.get(element), element);
			minHeap.add(node);

		}

		HuffTree node1, node2, node3 = null;
		// take the two minimum elements and merge
		while (minHeap.size() > 1) {
			node1 = minHeap.poll();
			node2 = minHeap.poll();
			node3 = new HuffTree(node1.root(), node2.root(), node1.weight() + node2.weight());
			minHeap.add(node3);
		}

		return node3;
	}

	public String serializeCodeMap(Map<Character, String> huffmanCode) {
		StringBuilder serialized = new StringBuilder();
		for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
			serialized.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
		}
		// Remove the trailing comma
		if (serialized.length() > 0) {
			serialized.setLength(serialized.length() - 1);
		}
		return serialized.toString();
	}

	public  Map<Character, String> deserializeMap(String serialized) {
		Map<Character, String> map = new HashMap<>();
		String[] entries = serialized.split(",");
		for (String entry : entries) {
			String[] keyValue = entry.split("=");
			map.put(keyValue[0].charAt(0), keyValue[1]);
		}
		return map;
	}

	public List<String> getOriginalContent(Map<Character, String> deserializedCodes, List<String> compressedFileLines) {
		
		//reverse the key and value in the code map
		Map<String, Character> reversedMap = new HashMap<>();
		List<String> originalContent = new ArrayList<>();
		
		for(Map.Entry<Character, String> entry : deserializedCodes.entrySet()) {
			reversedMap.put(entry.getValue(), entry.getKey());
		}
		
		String temp = "";
		
		compressedFileLines.remove(0);
		
		for(String line : compressedFileLines) {
			StringBuilder builder = new StringBuilder();
			
			for(char bit : line.toCharArray()) {
				temp += bit;
				
				if(reversedMap.containsKey(temp)) {
					builder.append(reversedMap.get(temp));
					temp = "";
				}
			}
			
			originalContent.add(builder.toString());
		}
		
		return originalContent;
	}
}
