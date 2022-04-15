package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public void toMinHeap(int[] data){
		// YOUR CODE HERE
		int start = data.length-1;
		buildHeap(data,start,1);
	}

	private void buildHeap(int[] arr, int position, int i){
		if(position != 0 && arr[position] < arr[parent(position)]){
			swapPositions(arr,position,parent(position));
			buildHeap(arr,parent(position),i);
		} else if(arr[arr.length-i] < arr[parent(arr.length-i)]){
			swapPositions(arr,arr.length-i,parent(arr.length-i));
			buildHeap(arr,parent(arr.length-i),i);
		} else {
			i++;
			if(i < arr.length-1)
				buildHeap(arr,arr.length-i,i);
		}
	}

	private int parent(int pos){
		int help;
		if(pos>1 && pos%2 == 0)
			help = pos/2-1;
		else if(pos > 1)
			help = pos/2;
		else
			help = 0;
		return help;
	}

	private int[] swapPositions(int[] arr,int childPosition, int parentPosition){
		int help = arr[parentPosition];
		arr[parentPosition] = arr[childPosition];
		arr[childPosition] = help;
		return arr;
	}

	@Override
	public void removeHeapElement(int position, int length, int[] minHeap)
	{
		// YOUR CODE HERE
	}

	@Override
	public void heapsort(int[] data)
	{
		// YOUR CODE HERE
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
