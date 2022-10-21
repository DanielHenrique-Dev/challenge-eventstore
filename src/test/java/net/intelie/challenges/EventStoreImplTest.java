package net.intelie.challenges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EventStoreImplTest {

	private EventStoreImpl events;
	
	@Before
	public void Initialize() {
		events = new EventStoreImpl(); 
	}

	@Test
	public void testingTheFunctionRemoveAllTest() {
		
		Event event1 = new Event("Data", 15);
		Event event2 = new Event("Data", 17);
		Event event3 = new Event("Data", 85);
		
		events.insert(event1);
		events.insert(event2);
		events.insert(event3);		
		
		events.removeAll("Data");
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		assertEquals(null, queryResult);
	}
	
	@Test
	public void testingTheInsertFunctionUsingANewKeyTest() {
		
		Event event1 = new Event("Start", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Start", 10, 20);
		
		queryResult.moveNext();
		
		assertEquals(15, queryResult.current().timestamp());
	} 
	
	@Test
	public void testingTheInsertFunctionUsingAnExistingKeyTest() {
		
		Event event1 = new Event("Start", 15);
		Event event2 = new Event("Start", 18); 
		
		events.insert(event1);
		events.insert(event2);
		
		EventIterator queryResult = events.query("Start", 10, 20);
		
		queryResult.moveNext();		
		queryResult.moveNext();
		
		assertEquals(18, queryResult.current().timestamp());
	}
	
	@Test
	public void testingIfQueryDoesNotFindAnyEventWithinTheParametersTest() {
		
		Event event1 = new Event("Start", 15);
		Event event2 = new Event("Start", 18);
		
		events.insert(event1);
		events.insert(event2);
		
		EventIterator queryResult = events.query("Start", 20, 30);
		
		assertEquals(null, queryResult);
		
		EventIterator queryResult2 = events.query("Ending", 10, 30);
		
		assertEquals(null, queryResult2);
	}

}
