package net.intelie.challenges;

import org.junit.Assert;
import org.junit.Test;

public class KeyGeneratorTest {

	@Test
	public void MakeAKeyTest() {
		KeyGenerator keyGenerator = new KeyGenerator();
		
		String key = keyGenerator.MakeAKey("Start");
		String key2 = keyGenerator.MakeAKey("Start");
		String key3 = keyGenerator.MakeAKey("Data");
		
		Assert.assertEquals("key_Start_1", key);
		Assert.assertEquals("key_Start_2", key2);
		Assert.assertEquals("key_Data_1", key3);
	}

}
