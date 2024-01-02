package MyPhoneDict;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import MyPhoneDict.DataStructures.AdressList;
import MyPhoneDict.Screens.NewPasswordScreen;
import MyPhoneDict.Screens.PasswordScreen;

public class Main {
	public static AdressList list = new AdressList();

	public static void main(String[] args) {

		if (CheckFirstLogin()) {
			NewPasswordScreen.main();
		} else {
			PasswordScreen.main();
		}

		list = list.readListFromFile();
	}

	private static boolean CheckFirstLogin() {
		File fileTxt = new File(".\\pass.txt");

		String scannedHashedPass = "";
		try {
			Scanner s = new Scanner(fileTxt);

			while (s.hasNextLine()) {
				scannedHashedPass = s.nextLine();
			}

			s.close();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}

		return scannedHashedPass.isEmpty();
	}
}