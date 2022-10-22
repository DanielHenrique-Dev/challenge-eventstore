package net.intelie.challenges;

import org.junit.Assert;
import org.junit.Test;

public class KeyGeneratorTest {

	//Confirming the value of the first key created, based on the type entered.
	@Test
	public void testingTheMakeAKeyMethodToGetTheFirstGeneratedKeyTest() {
		KeyGenerator keyGenerator = new KeyGenerator();
		
		String key = keyGenerator.MakeAKey("Start");
		
		Assert.assertEquals("key_Start_1", key);
	}

	//Confirming the value of the second key created, based on the type entered.
	@Test
	public void testingTheMakeAKeyMethodToGetTheSecondGeneratedKeyTest() {
		KeyGenerator keyGenerator = new KeyGenerator();

		String key = keyGenerator.MakeAKey("Start");
		String key2 = keyGenerator.MakeAKey("Start");

		Assert.assertEquals("key_Start_2", key2);
	}

	//Confirming the value of the first key created, based on the second type entered.
	@Test
	public void testingToGetTheFirstGeneratedKeyOfTheNewTypeEntered() {
		KeyGenerator keyGenerator = new KeyGenerator();

		String key = keyGenerator.MakeAKey("Start");
		String key2 = keyGenerator.MakeAKey("Start");
		String key3 = keyGenerator.MakeAKey("Data");

		Assert.assertEquals("key_Data_1", key3);
	}

}
