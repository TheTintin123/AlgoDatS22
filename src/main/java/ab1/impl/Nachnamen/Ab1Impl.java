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
			minHeapify(data,data.length,i);
		}

		for(int swapToPos = data.length-1; swapToPos > 0; swapToPos--){
			swapPositions(data,0,swapToPos);
			minHeapify(data,swapToPos,0);
		}
	}


	private ListNode getNode(int value){
		// allocate node
		ListNode newNode = new ListNode();

		// put in the data
		newNode.value = value;
		newNode.prev = newNode.next = null;
		return newNode;

	}
	@Override
	public LinkedList insert(LinkedList list, int value) {
		// YOUR CODE HERE
		ListNode current;
		ListNode newNode = getNode(value);

		// if list is empty
		if (list.head == null)
			list.head = newNode;

		// if list is empty
		if (list.tail == null)
			list.tail = newNode;

			// if the node is to be inserted at the beginning
			// of the doubly linked list
		else if (list.head.value >= newNode.value)
		{
			newNode.next = list.head;
			newNode.next.prev = newNode;
			list.head = newNode;
		} else
		{
			current = list.head;

			// locate the node after which the new node
			// is to be inserted
			while (current.next != null &&
					current.next.value < newNode.value)
				current = current.next;

			/* Make the appropriate links */
			newNode.next = current.next;

			// if the new node is not inserted
			// at the end of the list
			if (current.next != null)
				newNode.next.prev = newNode;

			current.next = newNode;
			newNode.prev = current;

		}
		return list;
	}

	@Override
	public LinkedList reverse(LinkedList list) {
		//Node current will point to head
		ListNode current = list.head, temp = null;

		//Swap the previous and next pointers of each node to reverse the direction of the list
		while(current != null) {
			temp = current.next;
			current.next = current.prev;
			current.prev = temp;
			current = current.prev;
		}
		//Swap the head and tail pointers.
		temp = list.head;
		list.head = list.tail;
		list.tail = temp;

		return list;
	}

	@Override
	public ListNode maximum(LinkedList list) {
		ListNode max, temp;

		temp = max = list.head;

		while (temp != null) {

			if (temp.value > max.value)
				max = temp;

			temp = temp.next;
		}
		return max;
	}

	@Override
	public void quicksort(int[] array)
	{
		doQuicksort(array, 0, array.length-1);
	}

	public static void doQuicksort(int[] array, int left, int right){
		int mid = array[(left + right) / 2];
		int leftValue = array[left];
		int rightValue = array[right];

		//Get median of three
		int pivot = getPivot(leftValue, mid, rightValue);

		int index = partition(array, left, right, pivot);


		//Recursively call quicksort with left part of the partitioned array
		if(left < index-1){
			doQuicksort(array, left, index-1);
		}


		//Recursively call quicksort with right part of the partitioned array
		if(right > index){
			doQuicksort(array, index, right);
		}
	}

	public static int partition(int[] array, int left, int right, int pivot){
		while(left <= right){

			//Search number (bottom um) which is greater than pivot
			while(array[left] < pivot){
				left++;
			}

			//Search number (top down) which is less than pivot
			while(array[right] > pivot){
				right--;
			}

			//Swap values
			if(left <= right){
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;

				//Adjust pointers
				left++;
				right--;
			}
		}
		return left;
	}

	public static int getPivot(int left, int mid, int right){

		//Find the median of three
		if (left <= mid && mid <= right){
			return mid;
		}else if (mid <= left && left <= right){
			return left;
		}else {
			return right;
		}
	}
	public static void main(String[] args){


	}
}


