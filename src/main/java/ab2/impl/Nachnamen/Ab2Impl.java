package ab2.impl.Nachnamen;

import ab2.Ab2;

import ab2.AbstractHashMap;

public class Ab2Impl extends AbstractHashMap implements Ab2 {

	private String type;
	private int count = 0;
	AbstractHashMap hashMap;

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
		hashMap = new Ab2Impl();
		initTable(findPrime(minSize,true));
		return hashMap;
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
		initTable(findPrime(minSize,false));
		return null;
	}

	@Override
	public int quickselect(int[] data, int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean put(int key, String value) {
		int index,times = 1;
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
					} if(getKey(index) == key){
						setKeyAndValue(index,key,value);
						break;
					}
					index--;

					if (index == key % capacity())
						return false;
				}
				break;
			case "quadratic":
				while (true){
					index = key % hashMap.capacity();
					if(isEmpty(index)){
						setKeyAndValue(index,key,value);
						count++;
						break;
					}

					index = findIndex(index,times);

					times++;

					if (index == key % hashMap.capacity())
						return false;
				}
				break;
			case "double":

				break;
		}
		return true;
	}

	@Override
	public String get(int key) {
		String returnString = "";
		int index,times=1,breaker = 0;
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
				while (true){
					index = key % hashMap.capacity();
					if(key == getKey(index)){
						returnString = getValue(index);
						break;
					}

					index = findIndex(index,times);

					times++;

					if (index == key % hashMap.capacity())
						return null;
				}
				break;
			case "double":

				break;
		}
		return returnString;
	}

	private int findIndex(int oldIndex, int times){
		int reducer = (int) (Math.pow((int) Math.ceil((double) times/2),2)*Math.pow(-1,times));
		if(oldIndex-reducer < 0)
			return hashMap.capacity()-(reducer-oldIndex);
		else if(oldIndex-reducer > hashMap.capacity()){
			return oldIndex-reducer-hashMap.capacity();
		} else
		 return	oldIndex - reducer;
	}

	@Override
	public int count() {
		return count;
	}
}
