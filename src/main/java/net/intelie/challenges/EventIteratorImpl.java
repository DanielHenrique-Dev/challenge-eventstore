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
		eventsIterator = null;
		eventConatinerKey = null;
		events = null;
	}

	@Override
	public boolean moveNext() {
		
		if(eventsIterator.hasNext()) {
			eventConatinerKey = eventsIterator.next();
			return true;
		}

		eventConatinerKey = null;
		return false;
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
