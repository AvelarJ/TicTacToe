//Made by Jordan Avelar
//CS2210a October 18 2020
//Student number 251083623

import java.util.*;

public class Dictionary implements DictionaryADT{
	
	//Instance variables
	private LinkedList<Data>[] hashTable;
	private int totalNumber, n;
	
	//Dictionary constructor
	public Dictionary(int size) {
		
		n = size;
		totalNumber = 0;
		hashTable = new LinkedList[size];
		
		for (int i=0;i<n;i++) {
			hashTable[i] = new LinkedList<Data>();
		}
		
	}
	
	//Put method puts new data into hashtable based off of the hashed key
	public int put(Data record) throws DuplicatedKeyException{
		int key = hash(record.getKey(), n);	//string key is immediately put through hash function
		
		//Checks if array at index is already empty
		if(hashTable[key].peek() == null) {
			hashTable[key].addFirst(record);
			totalNumber++;
			return 0;
		}
		
		//Iterates through to check if key is already present
		else if(hashTable[key].peek() != null) {
			ListIterator<Data> hashIter = hashTable[key].listIterator(0);
			int nextIndex;
			
			while(hashIter.hasNext()) {
				nextIndex = hashIter.nextIndex();
				
				if(hashTable[key].get(nextIndex).getKey().equals(record.getKey())) {
					throw new DuplicatedKeyException(record.getKey());
				}
				
				hashIter.next();
				
			}
			//Adds record to end of linkedlist in the array
			hashTable[key].addLast(record);
			totalNumber++;

		}
		return 1;
	}
	
	//Remove function looks for same key and removes it from linked list
	public void remove(String key) throws InexistentKeyException{
		int index = hash(key,n);
		
		if (hashTable[index].peek() == null){
			
			throw new InexistentKeyException(key);
		}
		
		else if(hashTable[index].peekFirst().getKey().equals(key)) {
			hashTable[index].pop();
			totalNumber--;
		}
		
		else {
			ListIterator<Data> hashIter = hashTable[index].listIterator(0);
			int nextIndex;
			
			while(hashIter.hasNext()) {
				nextIndex = hashIter.nextIndex();
				
				if(hashTable[index].get(nextIndex).getKey().equals(key)) {
					hashTable[index].remove(nextIndex);
					totalNumber--;
					break;
				}
				
				hashIter.next();
			}
		}
	}
	
	//Returns data object if same key is found
	public Data get(String key) {
		int index = hash(key,n);
		
		if(hashTable[index].peek() == null) return null;
		
		if(hashTable[index].getFirst().getKey().equals(key)) {
			return hashTable[index].getFirst();
		}
		
		else {
			ListIterator<Data> hashIter = hashTable[index].listIterator(0);
			int nextIndex;
			
			while(hashIter.hasNext()) {
				nextIndex = hashIter.nextIndex();
				
				if(hashTable[index].get(nextIndex).getKey().equals(key)) {
					return hashTable[index].get(nextIndex);
				}
				
				hashIter.next();
			}
		}
		
		return null;
	}
	
	//Returns total number of  data items
	public int numDataItems() {
		return totalNumber;
	}
	
	//Hash function in polynomial hashing formula with x = 33 to work well
	private int hash(String board, int tableSize) {
		int value = 0;
		int x = 33;
		
		for(int i = 0; i < board.length(); i++) {
			value = ( x * value + (int)board.charAt(i))% tableSize;
		}
		return value;
	}

}
