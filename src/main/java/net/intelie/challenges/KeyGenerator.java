package net.intelie.challenges;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyGenerator {

	private Map<String, Number> keyControl = new ConcurrentHashMap<>();
	
	public KeyGenerator() {
	}
	
	public String MakeAKey(String type) {
		
		if(!keyControl.containsKey(type)) {
			keyControl.put(type, 1);
			
		} else {
			int last_value = keyControl.get(type).intValue();
			last_value++;
			
			keyControl.put(type, last_value);			
		}
		
		return "key_"+ type + "_" + keyControl.get(type).intValue();
	}
}
