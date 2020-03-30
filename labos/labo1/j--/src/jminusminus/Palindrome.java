package jminusminus;

public class Palindrome {

	public String palindrome(String string) {
		int i = 0;
		boolean halfWord = true;
		while (halfWord) {
			char[] chList = string.toCharArray();
			char ch1 = toLowerCase(chList[i]);
			char ch2 = toLowerCase(chList[(string.length() - (i + 1))]);
			if (!(ch1 == ch2)) {
				return "";
			}

			if (i == string.length() / 2) {
				halfWord = false;
			}
			i = i + 1;
		}
		return string;
	}

	private char toLowerCase(char ch) {
		int i = (int)ch;
		if (i > 64 && 91 > i) {
			i += 32;
			ch = (char)i;
		}

		return ch;
	}
}
