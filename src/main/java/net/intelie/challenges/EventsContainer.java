package net.intelie.challenges;

import java.util.concurrent.ConcurrentHashMap;

public class EventsContainer extends ConcurrentHashMap<String, Event> {

	private static final long serialVersionUID = -1445005892248158466L;	
	
	KeyGenerator keyGenerator = new KeyGenerator();
	
	public EventsContainer() {
		super();
	}
	
	public void insertEvent(Event event) {	
		
		if(event.type().isBlank() || event.type().isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		String hashKey = keyGenerator.MakeAKey(event.type());
		
		this.put(hashKey, event);		
	}
}
