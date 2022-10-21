package net.intelie.challenges;

import java.util.Iterator;

public class EventIteratorImpl implements EventIterator {

	private EventsContainer events;
	private Iterator<String> eventsIterator;
	private String eventConatinerKey;	

	public EventIteratorImpl(EventsContainer events) {
		this.events = events;
		eventsIterator = events.keySet().iterator();
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public boolean moveNext() {
		
		if(!eventsIterator.hasNext()) {
			eventConatinerKey = null;
			return false;			
		}
				
		eventConatinerKey = eventsIterator.next();
		return true;			
	}

	@Override
	public Event current() {
		if(eventConatinerKey == null) {
			throw new IllegalStateException(); 
		}
		
		return events.get(eventConatinerKey);
	}

	@Override
	public void remove() {
		events.remove(eventConatinerKey);		
	}

}
