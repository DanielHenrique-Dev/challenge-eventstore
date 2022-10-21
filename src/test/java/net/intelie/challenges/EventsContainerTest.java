package net.intelie.challenges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EventsContainerTest {
	

	private EventsContainer eventsContainer;
	
	@Before
	public void Initialize() {
		eventsContainer = new EventsContainer();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testingTheInsertionOfAnEventWithTheParameterTypeEmptyTest() {
		
		Event event1 = new Event("", 15);
		
		eventsContainer.insertEvent(event1);
	} 
	
	@Test
	public void testingToFetchTheSecondEventEnteredTest() {
		
		Event event1 = new Event("Data", 15);
		Event event2 = new Event("Data", 30);
		Event event3 = new Event("Data", 40);
		
		eventsContainer.insertEvent(event1);
		eventsContainer.insertEvent(event2);
		eventsContainer.insertEvent(event3);
		
		assertEquals(true, eventsContainer.containsValue(event2));
	}

}
