package junit;

import junit.framework.TestCase;
import pass.UnaryAddition;

public class UnaryAdditionTest extends TestCase
{
	private UnaryAddition unaryAddition;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		unaryAddition = new UnaryAddition();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testAdd()
	{
		this.assertEquals(unaryAddition.add(4), 0);
		this.assertEquals(unaryAddition.add((4-5)), 1);
	}
}