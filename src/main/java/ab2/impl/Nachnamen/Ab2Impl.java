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
		initTable(findPrime(minSize,true));
		return this;
	}

	private int findPrime(int neighbour,boolean special){
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
		initTable(findPrime(minSize,false));
		return this;
	}

	@Override
	public int quickselect(int[] data, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean put(int key, String value) {
		int index,times = 0;
		switch (type) {
			case "linear":
				if(key < 0 || key > capacity()-1)
					return false;
				index = key % capacity();
				while (true) {
					if (index == -1)
						index = capacity()-1;

					if (isEmpty(index)) {
						setKeyAndValue(index, key, value);
						count++;
						break;
					}
					if(getKey(index) == key){
						setKeyAndValue(index,key,value);
						break;
					}
					index--;

					if (index == key % capacity())
						return false;
				}
				break;
			case "quadratic":
				if(key < 0 || key > capacity()-1)
					return false;
				while (true){
					index = key % capacity();

					index = findIndexQuadratic(index,times);

					if(isEmpty(index)){
						setKeyAndValue(index,key,value);
						count++;
						break;
					}
					if(getKey(index)==key){
						setKeyAndValue(index,key,value);
						break;
					}
					times++;

					if (count == capacity()-1)
						return false;
				}
				break;
			case "double":
				if(key < 0 || key > capacity()-1)
					return false;

				break;
		}
		return true;
	}

	@Override
	public String get(int key) {
		String returnString = "";
		int index,times=0,breaker = 0;
		switch (type) {
			case "linear":
				index = key % capacity();
				while (true) {
					if(index == -1)
						index = capacity()-1;

					if (getKey(index) != null && key == getKey(index)) {
						returnString = getValue(index);
						break;
					}
					index--;
					breaker++;

					if(breaker == capacity())
						return null;
				}
				break;
			case "quadratic":
				boolean found = false;
				for(int i = 0; i < capacity(); i++){
					if(getKey(i) != null && getKey(i) == key)
						found = true;
				}
				if(!found)
					return null;
				while (true){
					index = key % capacity();

					index = findIndexQuadratic(index,times);

					if(getKey(index) != null && key == getKey(index)){
						returnString = getValue(index);
						break;
					}
					times++;
				}
				break;
			case "double":

				break;
		}
		return returnString;
	}

	private int findIndexQuadratic(int oldIndex, int times){
		int reducer = (int) (Math.pow((int) Math.ceil((double) times/2),2)*Math.pow(-1,times));
		int rest;
		if (oldIndex - reducer < 0){
			rest = Math.abs(reducer)-oldIndex;
			rest = rest % capacity() == 0 ? capacity():rest % capacity();
			return capacity()-rest;
		}
		else if (oldIndex - reducer > capacity()) {
			rest = Math.abs(reducer)-(capacity()-oldIndex);
			return rest % capacity();
		} else
			return oldIndex - reducer;
	}

	@Override
	public int count() {
		return count;
	}
}
