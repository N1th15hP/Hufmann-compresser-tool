package com.codingchallenges.compressiontool;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codingchallenges.compressiontool.models.HuffTree;
import com.codingchallenges.compressiontool.service.CompressionService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@SpringBootApplication
public class CompressionToolApplication {
	
	
	private FileUtils fileUtils;
	
	@Autowired
	public void setFileUtils(FileUtils fileUtils) {
		this.fileUtils = fileUtils;
	}

	@Autowired
	public void setCompresser(CompressionService compresser) {
		this.compresser = compresser;
	}
	
	@Autowired
	public void setEncoder(HuffmanEncoder encoder) {
		this.encoder = encoder;
	}

	private CompressionService compresser;
	
	private HuffmanEncoder encoder;
	
	
	
	
	 @Bean
	    public CommandLineRunner run() {
	        return args -> {
	            File file = new File("D:\\Projects\\coding_challenges\\compression tool\\test1.txt");
	            List<String> lines = fileUtils.readLines(file);
	            Map<Character,Integer> frequencies = compresser.getCharacterFrequencies(lines);
	            HuffTree tree = compresser.buildTree(frequencies);
	            Map<Character, String> codes = encoder.generateHuffmanCode(tree); 
	            List<String> encodedContent =  encoder.encode(codes, lines);
	            String code = compresser.serializeCodeMap(codes);
	            String compressedFileName = fileUtils.createCompressedFile(file, code, encodedContent);
	            List<String> compressedFileLines  = fileUtils.readLines(new File(compressedFileName));
	            System.out.println(compressedFileLines);
	            Map<Character, String> deserializedCodes = compresser.deserializeMap(compressedFileLines.get(0));
	            List<String> originalLines = compresser.getOriginalContent(deserializedCodes, compressedFileLines);
	            System.out.println(originalLines);
	        };
	    }

	public static void main(String[] args) {
		SpringApplication.run(CompressionToolApplication.class, args);
	}

}
