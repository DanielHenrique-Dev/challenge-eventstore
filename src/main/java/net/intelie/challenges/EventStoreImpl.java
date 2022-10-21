package net.intelie.challenges;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventStoreImpl implements EventStore {
	
	private Map<String, EventsContainer> events = new ConcurrentHashMap<>();
	

	@Override
	public void insert(Event event) {
		
		if(	events.containsKey(event.type())) {
			events.get(event.type()).insertEvent(event);
			
		} else {
			
			EventsContainer newEventToInsert = new EventsContainer();
			newEventToInsert.insertEvent(event);
			events.put(event.type(), newEventToInsert);
		}
	}

	@Override
	public void removeAll(String type) {
		events.remove(type);
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
				
		if(type.isBlank() || type.isEmpty() || type == null || !events.containsKey(type)) {
			return null;
		}
		
		EventsContainer result_data = new EventsContainer();
		
		for (Map.Entry<String, Event> entry : events.get(type).entrySet()) {
			
			long entry_timestamp = entry.getValue().timestamp();
			
			if(	entry_timestamp >= startTime && entry_timestamp <= endTime) {
				result_data.insertEvent(entry.getValue());
			}
		}
		
		if(result_data.isEmpty()) {
			return null;
		}
		
		return new EventIteratorImpl(result_data);	
		
	}

}
