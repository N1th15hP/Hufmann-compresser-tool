package com.codingchallenges.compressiontool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingchallenges.compressiontool.service.CompressionService;

@SpringBootTest
class CompressionToolApplicationTests {

	@TempDir
	Path tempDir;
	
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private CompressionService compressionService;
	
	
	
	@Test
	void testCharacterFrequency() throws IOException {
		File tempFile = new File(tempDir.toFile(), "test1.txt");
		
		String expectedContent = "AAAAAAA";
		Files.writeString(tempFile.toPath(), expectedContent);
		List<String> lines = fileUtils.readLines(tempFile);
		assertEquals(lines.size(), 1);
		Map<Character, Integer> frequencyMap = compressionService.getCharacterFrequencies(lines);
		assertEquals(frequencyMap.get('A'), 7);
	}
	
	
	@Test
	void testReadNonExistentFile() {
		File file = new File("nonexistent.txt");
		List<String> lines = fileUtils.readLines(file);
		assertNull(lines);		
	}

}
