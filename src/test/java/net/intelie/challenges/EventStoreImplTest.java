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

	/**
	 * removing all elements, with type "Date".
	 */
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

	/**
	 * Adding a new event, on a non-existent key.
	 */
	@Test
	public void testingTheInsertFunctionUsingANewKeyTest() {
		
		Event event1 = new Event("Start", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Start", 10, 20);
		
		queryResult.moveNext();
		
		assertEquals(15, queryResult.current().timestamp());
	}

	/**
	 * Adding a new event, on a key that already exists.
	 */
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

	/**
	 * Fetching events with the query function, with parameters that will not generate results.
	 */
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

	/**
	 * Testing the query function success case.
	 */
	@Test
	public void testingIfThePositiveCaseOfTheQueryTest() {

		Event event1 = new Event("Start", 15);
		Event event2 = new Event("Start", 18);

		events.insert(event1);
		events.insert(event2);

		EventIterator queryResult = events.query("Start", 10, 20);

		queryResult.moveNext();

		assertEquals(15, queryResult.current().timestamp());
	}

}
