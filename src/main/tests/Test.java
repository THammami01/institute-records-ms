package main;

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		openDoc();
		copyDoc();
	}

	public static void openDoc() {
		String command = "start ...";
		try {
//			Process process =
			Runtime.getRuntime().exec("cmd /c " + command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDoc() {
		String command = "copy \"...\" \"...\"";
		try {
//			Process process =
			Runtime.getRuntime().exec("cmd /c " + command);
			System.out.println("Done !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}