package junit;

import junit.framework.TestCase;
import pass.Palindrome; 

public class PalindromeTest extends TestCase {
	private Palindrome palindrome;

	protected void setUp() throws Exception {
		super.setUp();
		palindrome = new Palindrome();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDivide() {
		this.assertEquals(palindrome.palindrome("malayalam"), "malayalam");
		this.assertEquals(palindrome.palindrome("Kayak"), "Kayak");
		this.assertEquals(palindrome.palindrome("aaaab"), "");
	}
}