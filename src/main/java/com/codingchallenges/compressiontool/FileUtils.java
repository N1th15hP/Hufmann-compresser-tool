package com.codingchallenges.compressiontool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {

	public List<String> readLines(File file) {
		List<String> lines = null;
		Path filePath = file.toPath();
		try {
			lines = Files.readAllLines(filePath);
		} catch (IOException e) {
			System.err.println("Failed to read file. reason : " + e.getMessage());
		}

		return lines;
	}

	public String createCompressedFile(File file, String code, List<String> encodedContent) {
		String fileName = file.getName();
		int lastDotIndex = fileName.lastIndexOf('.');
		String name = fileName.substring(0, lastDotIndex);
		String extension = fileName.substring(lastDotIndex);
		String compressedFileName = file.getParent() + File.separator + name + "_compressed" + extension;
		File compressedFile = new File(compressedFileName);

		try {
			compressedFile.createNewFile();

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFile))) {
				writer.write(code);
				writer.newLine();

				for (String line : encodedContent) {
					writer.write(line);
					writer.newLine();
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return compressedFileName;
	}

	public static Map<Character, String> deserializeMap(String serialized) {
		Map<Character, String> map = new HashMap<>();
		String[] entries = serialized.split(",");
		for (String entry : entries) {
			String[] keyValue = entry.split("=");
			map.put(keyValue[0].charAt(0), keyValue[1]);
		}
		return map;
	}
}
