package ab1;

import ab1.impl.Nachnamen.Ab1Impl;
import ab1.Ab1.ListNode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Ab1Tests {

	private Random rand = new Random(System.currentTimeMillis());

	private static Ab1 ab1Impl = new Ab1Impl();

	private static int NUM_TESTS = 50;
	private static int NUM_TESTS_LARGE = 500000;
	private static int ARRAY_SIZE_SMALL = 7500;
	private static int ARRAY_SIZE_LARGE = 250000;
	private static int ARRAY_SIZE_HUGE = 25_000_000;

	private static double heapPunkte = 0;

	private static double listPunkte = 0;
	private static boolean listWorks = false;

	private static double mergeSortPunkte = 0;
	private static boolean mergeSortWorks = false;

	@Test
	public void testIsHeapSmall()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(400), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			assertTrue(!ab1Impl.isHeap(1, test.length, test));

			test = getRandomHeap(ARRAY_SIZE_SMALL);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testIsHeapLarge()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			assertTrue(!ab1Impl.isHeap(1, test.length, test));

			test = getRandomHeap(ARRAY_SIZE_LARGE);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testToHeapSmall()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(400), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testToHeapLarge()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testToHeapSorted()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(800), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			Arrays.sort(test);
			ab1Impl.toHeap(test);
			assertTrue(ab1Impl.isHeap(1, test.length, test));
		}

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testHeapCornerCases()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(500), () -> {
		// empty array
		int[] test = new int[0];
		ab1Impl.toHeap(test);

		// array of size 1
		test = getRandomArray(1);
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));

		// array with all elements zero
		test = new int[ARRAY_SIZE_SMALL];
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));

		// array with all elements zero, except the last one
		test = new int[ARRAY_SIZE_SMALL];
		test[test.length - 1] = 1;
		ab1Impl.toHeap(test);
		assertTrue(ab1Impl.isHeap(1, test.length, test));

		heapPunkte += 0.5;
	    });
	}

	@Test
	public void testHeapSortSmall()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(600), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.heapsort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}

		heapPunkte += 1;
	    });
	}

	@Test
	public void testHeapSortLarge()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(6000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.heapsort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}

		heapPunkte += 1;
	    });
	}

	@Test
	public void testListInsert()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(5000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
			}
			Arrays.sort(test);
			for(int j = 0; j < test.length; ++j)
			{
				assertEquals(test[j], head.value);
				head = head.next;
			}
		}

		listPunkte += 3;
		listWorks = true;
	    });
	}

	@Test
	public void testListSearch()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(5000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
			}
			ListNode found = ab1Impl.search(head, test[0]);
			for(int j = 0; j < test.length; ++j)
			{
				if(found == head) break;
				head = head.next;
			}
			assertNotNull(head); // check that we found the node
		}

		listPunkte += 0.2;
	    });
	}

	@Test
	public void testListSearchNotFound()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(5000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
			}
			Arrays.sort(test);
			assertNull(ab1Impl.search(head, test[0]-1)); // check that we didn't find the node
		}

		listPunkte += 0.8;
	    });
	}

	@Test
	public void testListMinimum()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(5000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int min = Integer.MAX_VALUE;
			ListNode head = null;
			for(int j = 0; j < test.length; ++j)
			{
				head = ab1Impl.insert(head, test[j]);
				assertNotNull(head);
				if(min > test[j]) min = test[j];
			}
			ListNode foundMin = ab1Impl.minimum(head);
			assertEquals(min, foundMin.value);
		}

		listPunkte += 1;
	    });
	}

	@Test
	public void testMergeSortSmall()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(600), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_SMALL);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.mergesort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}

		mergeSortPunkte += 2;
	    });
	}

	@Test
	public void testMergeSortLarge()
	{
	    assertTimeoutPreemptively(Duration.ofMillis(6000), () -> {
		for(int i = 0; i < NUM_TESTS; ++i)
		{
			int[] test = getRandomArray(ARRAY_SIZE_LARGE);
			int[] copy = Arrays.copyOf(test, test.length);
			ab1Impl.mergesort(test);
			Arrays.sort(copy);
			assertArrayEquals(copy, test);
		}

		mergeSortPunkte += 3;
	    });
	}

	@AfterAll
	public static void printPoints()
	{
		if(!listWorks) listPunkte /= 2;

		int punkte = (int)(heapPunkte + listPunkte + mergeSortPunkte);
		System.out.println("Punkte Heap: " + heapPunkte);
		System.out.println("Punkte Mergesort: " + mergeSortPunkte);
		System.out.println("Punkte Liste: " + listPunkte);
		System.out.println("Gesamtpunkte: " + punkte);
	}


	private int[] getRandomArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; ++i)
			arr[i] = Math.abs(rand.nextInt(2 * size));
		return arr;
	}

	private int[] getRandomHeap(int size) {
		int[] arr = new int[size];
		arr[0] = Integer.MAX_VALUE - Math.abs(rand.nextInt(10));

		int pos = 0;
		while(true)
		{
			int l = 2*pos + 1;
			int r = 2*pos + 2;

			if(l >= arr.length) break;
			arr[l] = arr[pos] - Math.abs(rand.nextInt(9) + 1);

			if(r >= arr.length) break;
			arr[r] = arr[pos] - Math.abs(rand.nextInt(9) + 1);

			++pos;
		}

		return arr;
	}
}
