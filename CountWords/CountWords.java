

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CountWords {

	private String path;

	public CountWords(String fname) {
		path = fname;
	}

	public List<String> getResult() throws Exception {
		Map<String, Integer> words = new LinkedHashMap<>();
		List<String> result = new ArrayList<>();
		String regex = "([.,!?;\"*/\'()\\- ^0-9]+)"; //

		File file = new File(path);

		FileReader fileReader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String currentLine;
		while ((currentLine = bufReader.readLine()) != null) {
			String[] arr = currentLine.split(regex);
			for (int i = 0; i < arr.length; i++) {
				if (words.containsKey(arr[i])) {
					words.put(arr[i], words.get(arr[i]) + 1);
				} else {
					words.put(arr[i], 1);
				}
			}
			for (String dfd : words.keySet()) {
				result.add(dfd + " " + words.get(dfd));
			}

		}
		bufReader.close();
		return result;
	}
}
