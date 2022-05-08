package ab1.impl.Benischke_Ewinger_Plieschnegger;

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

	/**
	 * Funktion die einen neuen Node erzeugt und zurückgibt
	 * @param value Der Werte des neuen Nodes
	 * @return neuer Node
	 */
	private ListNode getNode(int value){
		//neue Node wird erstellt
		ListNode newNode = new ListNode();

		//value des Nodes wird mit value gefüllt
		newNode.value = value;
		newNode.prev = newNode.next = null;
		return newNode;

	}
	@Override
	public LinkedList insert(LinkedList list, int value) {
		ListNode current;
		ListNode newNode = getNode(value);

		//wenn die LinkedList leer ist
		if(list.tail == null && list.head == null){
			list.tail = newNode;
			list.head = newNode;
			return list;

			// wenn der Knoten am Anfang der doppelt verketteten Liste eingefügt werden soll
		}else if (list.head.value >= newNode.value) {
			newNode.next = list.head;
			newNode.next.prev = newNode;
			list.head = newNode;
			return list;

		} else {
			//Current wird mit head node initialisiert
			current = list.head;


			//durchgehen der Doubly Linked List mit der pointer current
			/*
			Wenn man beim Durchgehen auf current.next.value größer als newNode.value, das heißt der Wert
			des neuen Nodes, den man einfügen will, kleiner als der aktuelle current.next.value ist, dann muss
			man den gegebenen Knoten in folgen Schritten einfügen
			*/
			while (current.next != null && current.next.value < newNode.value){
				current = current.next;
			}


			//newNode.next wird zu current.next
			newNode.next = current.next;

			//wenn der neue Knoten nicht am Ende der Liste eingefügt wird
			if (current.next != null){
				newNode.next.prev = newNode;
			}


			current.next = newNode;
			newNode.prev = current;

		}
		return list;
	}

	@Override
	public LinkedList reverse(LinkedList list) {
		//Node current zeigt zum head
		ListNode current = list.head, temp = null;


		//Vertauscht den previous und next Zeiger jedes Knoten, um die Richtung der Liste umzukehren
		while(current != null) {
			temp = current.next;
			current.next = current.prev;
			current.prev = temp;
			current = current.prev;
		}
		//Vertausche the head and tail Zeiger.
		temp = list.head;
		list.head = list.tail;
		list.tail = temp;

		return list;
	}

	/**
	 * 1. Initialisierung des Temp und Max Zeiger (Pointer) auf den Kopfknoten (list.head)
	 * 2. Druchlaufen der gesamten Liste
	 * 3. Wenn value von temp größer als value von max ist, wird max zu temp
	 * 4. Weiter zum nächsten Knoten
	 * @param list die zu durchsuchende Liste
	 * @return max node
	 */
	@Override
	public ListNode maximum(LinkedList list) {
		ListNode max, temp;
		temp = max = list.head;
		//Initialisierung zwei Zeiger (Pointer) temp und max auf dem Kopfknoten

		//die gesamte doppelt verkettete Liste durchlaufen
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


