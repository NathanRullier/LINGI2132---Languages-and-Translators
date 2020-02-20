package jminusminus;

public class Palindrome {

	public String palindrome(String string) {

		for (int i = 0; i < string.length() / 2; i++) {
			if (Character.toLowerCase(string.charAt(i)) != Character
					.toLowerCase(string.charAt((string.length() - (i + 1))))) {
				return "";
			}
		}
		return string;
	}
}
