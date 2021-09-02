
/**
 * Implement a simple binary and linear search on MyArrayList object.
 * Some code is modified based on a reference book.
 */
package algorithim;

import structure.MyArrayList;

public class MySearch {

	private MySearch() {
	} // Prevent instantiation.

	/**
	 * Do linear search for the target on the ArrayList. Assuming the ArrayList is
	 * sorted in increasing order.
	 * 
	 * @param pal    The MyArrayList to be searched.
	 * @param target The object value we are looking for.
	 * @return The index of the object we found, -1 otherwise.
	 */
	public static int linearSearchSorted(MyArrayList pal, Comparable target) {
		int j;
		for (j = 0; j < pal.size() && target.compareTo(pal.elementAt(j)) > 0; j++)
			;

		if (j < pal.size() && target.compareTo(pal.elementAt(j)) == 0) {
			return j;
		}

		return -1;
	}

	/**
	 * Do binary search for the target on the ArrayList. Assuming the ArrayList is
	 * sorted in increasing order.
	 * 
	 * @param pal    The MyArrayList to be searched.
	 * @param target The object value we are looking for.
	 * @return The index of the object we found, -1 otherwise.
	 */
	public static int binarySearch(MyArrayList pal, Comparable target) {
		int first = 0, last = pal.size() - 1, middle;

		while (last - first >= 0) {
			middle = (first + last) / 2;

			if (target.compareTo(pal.elementAt(middle)) < 0) {
				last = middle - 1;
			} else if (target.compareTo(pal.elementAt(middle)) > 0) {
				first = middle + 1;
			} else {
				return middle;
			}
		} // end of the while loop
		return -1;
	}

}
