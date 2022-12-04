package com.control.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

	private FileUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Map<String, String> read(String filePath, String divisiveCharacter, String contain) {
		final var map = new HashMap<String, String>();

		try (var lines = Files.lines(Paths.get(filePath))) {
			lines.filter(line -> line.contains(contain)).map(line -> line.split(divisiveCharacter, 2))
					.forEach(keyValuePair -> map.put(keyValuePair[0], keyValuePair[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public static Map<String, String> read(String filePath, String divisiveCharacter, List<String> contains) {
		final var map = new HashMap<String, String>();

		for (var contain : contains) {
			map.putAll(read(filePath, divisiveCharacter, contain));
		};

		return map;
	}

}
