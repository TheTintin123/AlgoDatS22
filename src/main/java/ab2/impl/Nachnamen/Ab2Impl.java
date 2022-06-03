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
		if(neighbour < 2)
			return 2;
		while (true){
			neighbour--;
			if(checkPrime(neighbour))
				return neighbour;
		}
	}

	private boolean checkPrime(int n){
		int i,m=n/2;
		if(n==0||n==1)
			return false;
		else
			for (i = 2; i <= m; i++)
				if (n % i == 0)
					return false;
		return true;
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
	public int quickselect(int[] data, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean put(int key, String value) {
		int index = key % capacity(),times = 0;
		switch (type) {
			case "linear" -> {
				if (key < 0 || key > capacity() - 1)
					return false;
				while (true) {
					if (index == -1)
						index = capacity() - 1;

					if(hasBeenSet(index,key,value))
						break;

					index--;

					if (index == key % capacity())
						return false;
				}
			}
			case "quadratic", "double" -> {
				if (key < 0 || key > capacity() - 1)
					return false;
				while (true) {
					index = key % capacity();

					index = findIndex(index,key, times);

					if(hasBeenSet(index,key,value))
						break;

					times++;

					if (count == capacity()-1)
						return false;
				}
			}
		}
		return true;
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
				while (true) {
					if (index == -1)
						index = capacity() - 1;

					if (getKey(index) != null && key == getKey(index))
						return getValue(index);

					index--;
					breaker++;

					if (breaker == capacity())
						return null;
				}
			}
			case "quadratic", "double" -> {
				if(capacity() <= 0)
					return null;
				while (true) {
					index = key % capacity();

					index = findIndex(index,key,times);

					if (getKey(index) != null && key == getKey(index))
						return getValue(index);

					times++;

					if(times > capacity()-1)
						return null;
				}
			}
		}
		return null;
	}
	private int findIndex(int oldIndex, int key, int times) {
		int reducer, rest;
		if (type.equals("quadratic"))
			reducer = (int) (Math.pow((int) Math.ceil((double) times / 2), 2) * Math.pow(-1, times));
		else
			reducer = (key % findSmallerPrime(capacity()) + 1) * times;

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
