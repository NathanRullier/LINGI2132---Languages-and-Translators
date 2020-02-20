package junit;

import junit.framework.TestCase;
import pass.Modulo;

public class ModuloTest extends TestCase
{
	private Modulo modulo;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		modulo = new Modulo();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testModulo()
	{
		this.assertEquals(modulo.remainder(6, 2), 0);
		this.assertEquals(modulo.remainder(5, 3), 2);
		this.assertEquals(modulo.remainder(43, 43), 0);
	}
}