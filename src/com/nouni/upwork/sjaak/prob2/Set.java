package com.nouni.upwork.sjaak.prob2;

import java.util.Iterator;

public interface Set {

	/**
	 * @postcondition If the elements exists in the set it will be deleted.
	 *                Otherwise it will be inserted.
	 * 
	 * @param e
	 * @return true if the element was inserted; false if not
	 */
	boolean add(String e);

	/**
	 * @postcondition The data stored in the binary search tree was iterated in
	 *                monotonically non-decreasing order and was added in this order
	 *                to an object of the type Iterator<E>. This object of the type
	 *                Iterator<E> was subsequently returned.
	 **/
	Iterator<String> ascendingIterator();

	/**
	 * @postcondition The data stored in the binary search tree was iterated in
	 *                monotonically non-increasing order and was added in this order
	 *                to an object of the type Iterator<E>. This object of the type
	 *                Iterator<E> was subsequently returned.
	 **/
	Iterator<String> descendingIterator();
}
