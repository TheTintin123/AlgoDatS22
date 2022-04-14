package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public void toMinHeap(int[] data)
	{
		// YOUR CODE HERE
		int start = data.length-1;
		buildHeap(data,start);
	}

	private void buildHeap(int[] arr,int position){
		if(arr[position] < parent(position)){
			swapPositions(arr,position,parent(position));
			buildHeap(arr,parent(position));
		} else {
			buildHeap(arr,position-1);
		}
	}

	private int parent(int pos){
		return pos/2;
	}

	private int leftChild(int pos){
		return pos*2;
	}

	private int rightChild(int pos){
		return pos*2+1;
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
