
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Finder {

	private String path;
	private static String[] chars;

	public Finder(String fname) {
		path = fname;
	}

	public int getIfCount() throws IOException {
		int if_count = 0;
		String clear_line = "";
		File file = new File(path);

		FileReader fileReader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String currentLine;
		while ((currentLine = bufReader.readLine()) != null) {
			String[] words = currentLine.split("\\s+");
			for (String word : words) {
				clear_line += word;
			}
			chars = clear_line.split("");
			clear_line = "";
			for (int i = 0; i < chars.length; i++) {
				if (chars.length - i + 1 > 5) {
					if ((chars[i].contains("/") && (chars[i + 1].contains("/") || chars[i + 1].contains("*")))) {
						break;
					} else if (chars[i].contains("\"")) {
						while (!chars[i + 1].contains("\"")) {
							i++;
						}
						if (chars[i + 1].contains("\"")) {
							i++;
						}
					} else {
						if (chars[i].contains("i")) {
							if (chars[i + 1].contains("f")) {
								if (chars[i + 2].contains("(")) {
									int j = i + 3;
									while (!chars[j].contains(")")) {
										j++;
									}
									if_count++;
								}
							}
						}
					}
				}
			}
		}
		bufReader.close();
		return if_count;
	}

	public int getStringCount(String string) throws Exception {

		int string_count = 0;
		File file = new File(path);

		FileReader fileReader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(fileReader);

		String currentLine;
		while ((currentLine = bufReader.readLine()) != null) {
			String clear_line = "";
			String[] words = currentLine.split("\\s+");
			for (String word : words) {
				clear_line += word;
			}
			String[] chars1 = clear_line.split("");
			for (int i = 0; i < chars1.length; i++) {
				if (chars1[i].contains("v")) {
					if (chars1[i + 1].contains("a")) {
						if (chars1[i + 2].contains("r")) {
							if (chars1[i + 3].contains("i")) {
								if (chars1[i + 4].contains("a")) {
									if (chars1[i + 5].contains("n")) {
										if (chars1[i + 6].contains("t")) {
											string_count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		bufReader.close();
		return string_count;
	}

}
