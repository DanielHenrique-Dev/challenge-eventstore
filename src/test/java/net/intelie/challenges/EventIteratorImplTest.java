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

}
