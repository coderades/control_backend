package com.control.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileUtil {

	public static Map<String, String> read(String filePath, String divisiveCharacter, String contains) {
		Map<String, String> map = new HashMap<>();

		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
			lines.filter(line -> line.contains(contains)).forEach(line -> {
				var keyValuePair = line.split(divisiveCharacter, 2);
				map.put(keyValuePair[0], keyValuePair[1]);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public static Map<String, String> read(String filePath, String divisiveCharacter, List<String> contains) {
		Map<String, String> map = new HashMap<>();

		for (var contain : contains) {
			try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
				lines.filter(line -> line.contains(contain)).forEach(line -> {
					var keyValuePair = line.split(divisiveCharacter, 2);
					map.put(keyValuePair[0], keyValuePair[1]);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

}
