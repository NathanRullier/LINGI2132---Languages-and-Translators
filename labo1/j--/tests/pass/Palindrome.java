package pass;

import jminusminus.CharReader;

public class Palindrome {

	public String palindrome(String string) {
		int i = 0;
		boolean halfWord = true;
		while (halfWord) {
			char[] chList = string.toCharArray();
			char ch1 = chList[i];
			char ch2 = chList[(string.length() - (i + 1))];
			if (!(ch1 == ch2) ) {
				return "";
			}

			if (i == string.length() / 2) {
				halfWord = false;
			}
			i = i + 1;
		}
		return string;
	}
}