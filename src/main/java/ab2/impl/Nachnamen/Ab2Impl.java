package ab2.impl.Nachnamen;

import ab2.Ab2;

import ab2.AbstractHashMap;

public class Ab2Impl extends AbstractHashMap implements Ab2 {

	private String type;
	private int count = 0;

	@Override
	public AbstractHashMap newHashMapLinear(int minSize) {
		// TODO Auto-generated method stub
		type = "linear";
		clear();
		count = 0;
		initTable(minSize);
		return this;
	}

	@Override
	public AbstractHashMap newHashMapQuadratic(int minSize) {
		// TODO Auto-generated method stub
		type = "quadratic";
		clear();
		count = 0;
		initTable(findLargerPrime(minSize,true));
		return this;
	}

	private int findLargerPrime(int neighbour, boolean special){
		if(neighbour < 0)
			neighbour = 0;
		while (true){
			if(special) {
				if (checkPrime(neighbour) && neighbour % 4 == 3)
					return neighbour;
			}else
				if (checkPrime(neighbour))
					return neighbour;
			neighbour++;
		}
	}

	private int findSmallerPrime(int neighbour){
		if(neighbour <= 2)
			return 2;
		while (true){
			neighbour--;
			if(checkPrime(neighbour) || neighbour == 2)
				return neighbour;
		}
	}

	public static boolean checkPrime(int num)
	{
		for (int i = 2; i<= num/i; i++)
		{
			if (num % i == 0)
			{
				return false;
			}
		}
		return num > 1;
	}

	@Override
	public AbstractHashMap newHashMapDouble(int minSize) {
		// TODO Auto-generated method stub
		type = "double";
		clear();
		count = 0;
		initTable(findLargerPrime(minSize,false));
		return this;
	}

@Override
	public  int quickselect(int[] data, int i) {
		return (data[check(data, 0, data.length - 1, i)]);
	}
	static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static void insertSort(int[] a, int beg, int end) {
		for (int i = beg; i < end; ++i) {
			int key = a[i];
			int j = i - 1;
			while (j >= beg && a[j] > key) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = key;
		}
	}


	//Lomuto partition
	static int partition(int[] a, int l, int r) {
		int pivot = a[r];
		int i = (l - 1);
		for (int j = l; j < r; j++) {
			if (a[j] <= pivot) {
				i++;
				swap(a, i, j);
			}
		}
		swap(a, i + 1, r);
		return i + 1;
	}

	static int check(int[] a, int l, int r, int elem) {
		// in-place median of medians algorithm
		// a[] - array
		// l - element "from"
		// r - element "to"
		// elem - kth element we're looking for
		if (r - l <= 5) {
			insertSort(a, l, r + 1);
			return l + elem - 1;
		} else {
			int count = l;
			for (int i = l; i < r; i = i + 5) {
				if (i + 5 < r) {
					insertSort(a, i, i + 5);
					swap(a, count, i + 2);
					// swapping the found medians with elements
					// at the beginning of the considered array (from "l")
					// instead of creating a new array to store medians
				} else {
					insertSort(a, i, r);
					swap(a, count, (i + r) / 2);
				}
				count++;
			}
			int medianaMedian = check(a, l, count - 1, (count - l + 1) / 2);
			swap(a, medianaMedian, r);
			int pivot = partition(a, l, r);
			int elem2 = pivot - l + 1;
			if (elem == elem2) return pivot;
			else if (elem < elem2) return check(a, l, pivot - 1, elem);
			else return check(a, pivot + 1, r, elem - elem2);
		}
	}

	@Override
	public boolean put(int key, String value) {
		int index = key % capacity(),times = 0;
		switch (type) {
			case "linear" -> {
				if (key < 0 || key > capacity() - 1)
					return false;
				while (index <= key % capacity()) {
					if (index == -1)
						index = capacity() - 1;

					if(hasBeenSet(index,key,value))
						return true;

					index--;
				}
			}
			case "quadratic" -> {
				if (key < 0 || key > capacity() - 1)
					return false;
				while (count <= capacity()-1) {
					index = key % capacity();

					index = findIndex(index,key, times,0);

					if(hasBeenSet(index,key,value))
						return true;

					times++;
				}
			}
			case "double" -> {
				if (key < 0 || key > capacity() - 1)
					return false;
				int smallerPrime = findSmallerPrime(capacity());
				while (count <= capacity()-1) {
					index = key % capacity();

					index = findIndex(index,key, times,smallerPrime);

					if(hasBeenSet(index,key,value))
						return true;

					times++;
				}
			}
		}
		return false;
	}

	private boolean hasBeenSet(int index, int key, String value){
		if(isEmpty(index)){
			setKeyAndValue(index, key, value);
			count++;
			return true;
		}
		if(getKey(index) == key){
			setKeyAndValue(index, key, value);
			return true;
		}
		return false;
	}

	@Override
	public String get(int key) {
		int index = key % capacity(),times=0,breaker = 0;
		switch (type) {
			case "linear" -> {
				while (breaker != capacity()) {
					if (index == -1)
						index = capacity() - 1;

					if (getKey(index) != null && key == getKey(index))
						return getValue(index);

					index--;
					breaker++;
				}
			}
			case "quadratic" -> {
				if(capacity() <= 0)
					return null;
				while (times <= capacity()-1) {
					index = key % capacity();

					index = findIndex(index,key,times,0);

					if (getKey(index) != null && key == getKey(index))
						return getValue(index);

					times++;
				}
			}
			case "double" -> {
				if(capacity() <= 0)
					return null;
				int smallerPrime = findSmallerPrime(capacity());
				while (times <= capacity()-1) {
					index = key % capacity();

					index = findIndex(index,key,times,smallerPrime);

					if (getKey(index) != null && key == getKey(index))
						return getValue(index);

					times++;
				}
			}
		}
		return null;
	}
	private int findIndex(int oldIndex, int key, int times, int smallerPrime) {
		int reducer, rest;
		if (type.equals("quadratic"))
			reducer = (int) (Math.pow((int) Math.ceil((double) times / 2), 2) * Math.pow(-1, times));
		else
			reducer = (key % smallerPrime + 1) * times;

		if (oldIndex - reducer < 0) {
			rest = Math.abs(reducer) - oldIndex;
			rest = rest % capacity() == 0 ? capacity() : rest % capacity();
			return capacity() - rest;
		} else if (oldIndex - reducer >= capacity()) {
			rest = Math.abs(reducer) - (capacity() - oldIndex);
			return rest % capacity();
		} else
			return oldIndex - reducer;
	}

	@Override
	public int count() {
		return count;
	}
}
