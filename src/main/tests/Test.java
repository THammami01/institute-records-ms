package main.tests;

import java.io.IOException;
import java.util.Calendar;

public class Test {
	public static void main(String[] args) {
//		openDoc();
//		copyDoc();
		getDatetime();
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

	public static void getDatetime() {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH) + 1);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
	}
}