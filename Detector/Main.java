/**
 * 
 */

import java.util.Scanner;

/**
 * This class is used to run the detector program
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		displayInfo();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the path to begin malware detection");
		String path = scanner.nextLine();
		scanner.close();
		
		System.out.println("Scanning in progress...");
		Detector detector = new Detector(path);
		detector.detect();
		System.out.println("Scan Complete.");
		System.out.println("The result of the scan is available in C:\\keydetect\\");
		System.out.println("\t\t Result of the scan : result.txt");
		System.out.println("\t\t Errors while performing scan : errors.txt");
		System.out.println("\t\t affected files list : affected.txt");
	}

	private static void displayInfo() {
		System.out.println("#################################################################################");
		System.out.println("#\t\tWelcome to Keydetect malware scanner!\t\t\t\t#");
		System.out.println("#\t\tVersion 1.0\t\t\t\t\t\t\t#");
		System.out.println("#\t\tDeveloped by: Deepak Shankar, Payal Kothari and Meghana Satish  #");
		System.out.println("#################################################################################\n\n");
		

	}

}
