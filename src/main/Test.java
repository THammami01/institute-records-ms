package main;

import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		openDoc();
		copyDoc();
	}

	public static void openDoc() {
		String command = "start C:\\Users\\thammami\\Desktop\\20sshine\\enactus.png";
		try {
//			Process process =
			Runtime.getRuntime().exec("cmd /c " + command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyDoc() {
		String command = "copy \"C:\\Users\\thammami\\Desktop\\Relevé de Notes 1.png\" \"C:\\SGRN\\2GLSI3\\09525787\"";
		try {
//			Process process =
			Runtime.getRuntime().exec("cmd /c " + command);
			System.out.println("Done !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}