package net.intelie.challenges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EventIteratorImplTest {
	
	private EventStoreImpl events;
	
	@Before
	public void Initialize() {
		events = new EventStoreImpl(); 
	}

	/**
	 * Testing the return IllegalStateException, when fetching an inexistent event from the fetching.
	 */
	@Test(expected = IllegalStateException.class)
	public void TestingIllegalReturnOfCurrentFunctionTest() {
		Event event1 = new Event("Data", 15);		
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		queryResult.moveNext();
		queryResult.moveNext();
		
		queryResult.current();
	}

	/**
	 * Testing the positive return of the current event.
	 */
	@Test
	public void TestingReturnOfCurrentFunctionTest() {
		Event event1 = new Event("Data", 15);

		events.insert(event1);

		EventIterator queryResult = events.query("Data", 10, 90);

		queryResult.moveNext();

		assertEquals(15, queryResult.current().timestamp());
	}

	/**
	 * Testing the positive return of the moveNext function, going to the next event.
	 */
	@Test
	public void testingIterationNextMoveWhenEventToIterateTest() {
		Event event1 = new Event("Data", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		assertEquals(true, queryResult.moveNext());
	}

	/**
	 * Testing the negative return of the moveNext function, going to the next event.
	 */
	@Test
	public void testingIterationNextMoveWhenNotEventToIterateTest() {
		Event event1 = new Event("Data", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		queryResult.moveNext();
		
		assertEquals(false, queryResult.moveNext());
	}

	/**
	 * Removing the event, using the remove function of the EventIterator class.
	 */
	@Test
	public void testingIfEventIsRemovedFromInteractionTest() {
		Event event1 = new Event("Data", 15);

		events.insert(event1);

		EventIterator queryResult = events.query("Data", 10, 20);

		queryResult.moveNext();

		queryResult.remove();

		assertEquals(null, queryResult.current());
	}

	/**
	 * testing to return IllegalStateException, when there is no event to be removed.
	 */
	@Test(expected = IllegalStateException.class)
	public void testingThatThereIsNoEventToBeRemovedFromTheInteractionTest() {
		Event event1 = new Event("Data", 15);

		events.insert(event1);

		EventIterator queryResult = events.query("Data", 10, 20);

		queryResult.moveNext();
		queryResult.moveNext();

		queryResult.remove();
	}

	/**
	 * Testing the close function, making all class attributes null.
	 */
	@Test(expected = IllegalStateException.class)
	public void testingClassClosureWithCloseMethodTest() throws Exception {
		Event event1 = new Event("Data", 15);
		Event event2 = new Event("Data", 11);
		Event event3 = new Event("Data", 12);
		Event event4 = new Event("Data", 13);

		events.insert(event1);
		events.insert(event2);
		events.insert(event3);
		events.insert(event4);

		EventIterator queryResult = events.query("Data", 10, 20);

		queryResult.moveNext();

		queryResult.close();

		queryResult.current();
	}

}
