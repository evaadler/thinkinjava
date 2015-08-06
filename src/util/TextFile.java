package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	// Read a file as a single string;
	public static String read(String filename) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(
					filename).getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	// Write a single file in one method call;
	public static void write(String filename, String text) {
		PrintWriter out;
		try {
			out = new PrintWriter(new File(filename).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	// Read a file, split by any regular expression;
	public TextFile(String filename, String splitter) {
		super(Arrays.asList(read(filename).split(splitter)));
		if (get(0).equals(""))
			remove(0);
	}

	// Normally read by lines;
	public TextFile(String fileName) {
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName)
					.getAbsoluteFile());
			try {
				for (String item : this) {
					out.println(item);
				}
			} finally {
				out.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String file = read("TextFile.java");
		write("text.txt", file);
		TextFile text = new TextFile("text.txt");
		text.write("test2.txt");
		//Break into unique sorted list of words
		TreeSet<String> words = new TreeSet<String>(
			new TextFile("TextFile.java", "\\W"));

		System.out.println(words.headSet("a"));
	}
}
