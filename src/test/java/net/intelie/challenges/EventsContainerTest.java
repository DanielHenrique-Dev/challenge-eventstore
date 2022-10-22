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

	//testing the validation of the type inserted in the insertEvent method
	@Test(expected = IllegalArgumentException.class)
	public void testingTheInsertionOfAnEventWithTheParameterTypeEmptyTest() {
		
		Event event1 = new Event("", 15);
		
		eventsContainer.insertEvent(event1);
	} 

	//testing if the event was inserted in the container
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
