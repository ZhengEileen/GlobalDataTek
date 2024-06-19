package main;

import java.io.IOException;

import enrollment.Enrollment;

public class Main {
	public static void main(String[] args) {
		try {
			Enrollment.processEnrollmentFile("src/resources/enrollment.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
