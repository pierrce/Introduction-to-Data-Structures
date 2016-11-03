///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MapBenchmark.java
// File:             SimpleHashMap.java
// Semester:         CS367 Fall 2014
//
// Author:           Alec Pierce apierce2@wisc.edu
// CS Login:         apierce
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Dan Hayes dahayes@wisc.edu
// CS Login:         dhayes
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.
 *
 * The SimpleHashMap takes two generic parameters, K
 * and V, standing for the types of keys and values respectively.
 *
 */
public class SimpleHashMap<K extends Comparable<K>,V> implements SimpleMapADT<K , V> {


	//Initiallized Variables
	private int[] tableSizes = { 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
			205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881, 
			421439783, 842879579, 1685759167};
	private double lf = 0.75;
	private int currSize = 0;
	private LinkedList<Entry<K,V>>[] buckets;

	//Constructor for HashMap.
	@SuppressWarnings("unchecked")
	public SimpleHashMap()
	{
		buckets = new LinkedList[tableSizes[currSize]];
	} 

	//Returns location of key.
	private int hash(K k) {

		int value = k.hashCode() % buckets.length;
		if(value < 0) value += buckets.length;

		return value;
	} 


	/**
	 * Returns the value to which the specified key is mapped, or null if this
	 * map contains no mapping for the key.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null
	 * if this map contains no mapping for the key
	 * @throws NullPointerException if the specified key is null
	 */

	public V get(K key) {

		if(key == null) throw new NullPointerException();

		//Gets the location of the buckets for key.
		int hash = hash(key);

		//If no buckets as location hash, returns null.
		if(buckets[hash] == null) return null;

		//For each Entry in buckets at location hash, returns value if equal.
		for(Entry<K,V> bucket: buckets[hash]) {

			if(bucket.getKey().compareTo(key) <= 0) return bucket.getValue();

		}

		return null;
	}   



	/**
	 * Associates the specified value with the specified key in this map.
	 * Neither the key nor the value can be null. If the map
	 * previously contained a mapping for the key, the old value is replaced.
	 *
	 * @param key key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key, or
	 * null if there was no mapping for key.
	 * @throws NullPointerException if the key or value is null
	 */

	public V put(K key, V value) {

		//Throws null pointer when key or value is null.
		if(key == null || value == null) throw new NullPointerException();

		//Location of buckets with key.
		int hash = hash(key);
		int count = 0;

		//If buckets don't exist create buckets.
		if(buckets[hash] == null) buckets[hash] = new LinkedList<Entry<K,V>>();

		//For each bucket at hash, if equal replace and return old value.
		for(Entry<K,V> bucket: buckets[hash]){

			if(bucket.getKey().compareTo(key) <= 0) {

				V v = bucket.getValue();
				bucket.setValue(value);
				return v;
			}

		}

		//If entry doesn't exist yet, create new entry.
		buckets[hash].add(new Entry<K,V>(key, value));

		//Check size of buckets, resize if have to.
		for(LinkedList<Entry<K,V>> list: buckets) if(list != null)count++;
		if((double)count/(double)buckets.length > lf) rehash(buckets);

		return null;
	}   




	/**
	 * Removes the mapping for the specified key from this map if present. This
	 * method does nothing if the key is not in the map.
	 *
	 * @param key key whose mapping is to be removed from the map
	 * @return the previous value associated with key, or null
	 * if there was no mapping for key.
	 * @throws NullPointerException if key is null
	 */

	public V remove(K key) {

		if(key == null) throw new NullPointerException();

		//Gets location of key.
		int hash = hash(key);

		V prevValue;

		//Removes bucket of key location, and returns its value.
		if(buckets[hash] != null) {
			for(Entry<K,V> bucket: buckets[hash]) {

				if(bucket.getKey().compareTo(key) <= 0) {
					prevValue = bucket.getValue();
					buckets[hash] = null;
					return prevValue;
				}
			}
		}
		return null;
	}   

	/**
	 * Returns the greatest key less than or equal to the given key, or null if there is no such key. 
	 * Throws NullPointerException if key is null. 
	 * @param key key whose floor should be found
	 * @return the largest key smaller than the one passed to it
	 * @throws NullPointerException if key is null
	 */
	public K floorKey(K key){

		if(key == null) throw new NullPointerException();

		boolean equals = false;
		K floor = null;
		K holder = null;

		//Goes through all linkedlists and buckets, and returns other key 1 lower than key.
		for(LinkedList<Entry<K,V>> list: buckets) {

			if(list != null){

				for(Entry<K,V> bucket: list) {

					if(bucket.getKey() != null && bucket.getKey().compareTo(key) <= 0) {

						if(bucket.getKey().compareTo(key) == 0) equals = true; 
						holder = bucket.getKey();
						if(!equals) floor = holder;

					}
				}
			}

		} 

		if(equals) return floor;
		return holder;

	}

	/**
	 * Returns newly extended version of the hashMap. 
	 * 
	 * @param oldBuckets The old congested version of the hashMap.
	 * @return The newly extended version of the hashMap.
	 */
	@SuppressWarnings("unchecked")
	private LinkedList<Entry<K,V>>[] rehash(LinkedList<Entry<K,V>>[] oldBuckets){


		currSize++;
		buckets = new LinkedList[tableSizes[currSize]];

		//Rehashes everything from oldHashMap to newHashMap.
		for(LinkedList<Entry<K,V>> l: oldBuckets) {
			if(l != null){
				for(Entry<K,V> bucket: l) {
					put(bucket.getKey(), bucket.getValue());
				}
			}
		}

		return oldBuckets;
	}
}
