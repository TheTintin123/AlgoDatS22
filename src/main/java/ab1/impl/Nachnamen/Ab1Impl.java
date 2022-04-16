package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public void toMinHeap(int[] data){
		// YOUR CODE HERE

		// We find the last Node that is a parent
		int lastParentNode = data.length/2-1;

		// We now build the heap from this position backwards
		for(int i = lastParentNode; i >= 0; i--){
			minHeapify(data,data.length,i);
		}
	}

	private void minHeapify(int[] heap, int length, int parentPos){
		int leftChildPos = parentPos * 2 + 1;
		int rightChildPos = parentPos * 2 + 2;

		// Find the smallest Element (Parent/LeftChild/RightChild)
		int smallestPos = parentPos;
		if (leftChildPos < length && heap[leftChildPos] < heap[smallestPos]) {
			smallestPos = leftChildPos;
		}
		if (rightChildPos < length && heap[rightChildPos] < heap[smallestPos]) {
			smallestPos = rightChildPos;
		}

		// If the smallest Element is the parent, return
		if (smallestPos == parentPos) {
			return;
		}

		// If it's not the parent, swap positions (Parent <-> SmallestChild)
		swapPositions(heap, parentPos, smallestPos);

		// ParentPos is now at the childPos we switched with
		parentPos = smallestPos;

		// new MethodCall with new parentPos
		minHeapify(heap,length,parentPos);
	}

	private void swapPositions(int[] arr,int firstPos, int secondPos){
		int help = arr[secondPos];
		arr[secondPos] = arr[firstPos];
		arr[firstPos] = help;
	}

	@Override
	public void removeHeapElement(int position, int length, int[] minHeap)
	{
		// YOUR CODE HERE

		// swap positions with position and length-1
		swapPositions(minHeap,position,length-1);

		// build new minHeap with a length-1 and the position as parentPosition
		minHeapify(minHeap,length-1,position);
	}

	@Override
	public void heapsort(int[] data)
	{
		// YOUR CODE HERE

		// We find the last Node that is a parent
		int lastParentNode = data.length/2-1;

		// We now build the heap from this position backwards
		for(int i = lastParentNode; i >= 0; i--){
			maxHeapify(data,data.length,i);
		}

		for(int swapToPos = data.length-1; swapToPos > 0; swapToPos--){
			swapPositions(data,0,swapToPos);
			maxHeapify(data,swapToPos,0);
		}
	}

	private void maxHeapify(int[] heap, int length, int parentPos){
		int leftChildPos = parentPos * 2 + 1;
		int rightChildPos = parentPos * 2 + 2;

		// Find the largest Element (Parent/LeftChild/RightChild)
		int largestPos = parentPos;
		if (leftChildPos < length && heap[leftChildPos] > heap[largestPos]) {
			largestPos = leftChildPos;
		}
		if (rightChildPos < length && heap[rightChildPos] > heap[largestPos]) {
			largestPos = rightChildPos;
		}

		// If the largest Element is the parent, return
		if (largestPos == parentPos) {
			return;
		}

		// If it's not the parent, swap positions (Parent <-> LargestChild)
		swapPositions(heap, parentPos, largestPos);

		// ParentPos is now at the childPos we switched with
		parentPos = largestPos;

		// new MethodCall with new parentPos
		minHeapify(heap,length,parentPos);
	}

	@Override
	public LinkedList insert(LinkedList list, int value)
	{
		// YOUR CODE HERE
		return null;
	}

	@Override
	public ListNode reverse(LinkedList list, ListNode tail)
	{
		// YOUR CODE HERE
		return null;
	}

	@Override
	public ListNode maximum(LinkedList list)
	{
		// YOUR CODE HERE
		return null;
	}

	@Override
	public void quicksort(int[] array)
	{
		// YOUR CODE HERE
	}
}
