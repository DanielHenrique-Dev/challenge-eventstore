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

	@Test(expected = IllegalStateException.class)
	public void TestingIllegalReturnOfCurrentFunctionTest() {
		Event event1 = new Event("Data", 15);		
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		queryResult.moveNext();
		queryResult.moveNext();
		
		queryResult.current();
	}

	@Test
	public void TestingReturnOfCurrentFunctionTest() {
		Event event1 = new Event("Data", 15);

		events.insert(event1);

		EventIterator queryResult = events.query("Data", 10, 90);

		queryResult.moveNext();

		assertEquals(15, queryResult.current().timestamp());
	}

	@Test
	public void testingIterationNextMoveWhenEventToIterateTest() {
		Event event1 = new Event("Data", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		assertEquals(true, queryResult.moveNext());
	}
	
	@Test
	public void testingIterationNextMoveWhenNotEventToIterateTest() {
		Event event1 = new Event("Data", 15);
		
		events.insert(event1);
		
		EventIterator queryResult = events.query("Data", 10, 90);
		
		queryResult.moveNext();
		
		assertEquals(false, queryResult.moveNext());
	}

	@Test
	public void testingIfEventIsRemovedFromInteractionTest() {
		Event event1 = new Event("Data", 15);

		events.insert(event1);

		EventIterator queryResult = events.query("Data", 10, 20);

		queryResult.moveNext();

		queryResult.remove();

		assertEquals(null, queryResult.current());
	}

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
