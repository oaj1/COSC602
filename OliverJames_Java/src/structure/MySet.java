package structure;

/**
 * Class utilized to implement the set ADT
 * 
 * @author ollie
 *
 */

public class MySet implements Cloneable {

	private MyArrayList setContainer;

	/**
	 * Default Constructor
	 */
	public MySet() {

		setContainer = new MyArrayList();

	}

	/**
	 * Overridden toString Method for MySet ADT
	 */
	public String toString() {
		String str = "++++++++++++++++++++++++++++++++++++++++++\n" + "The current Set contains the following: \n";
		str += "size = " + setContainer.size() + "\n";
		for (int i = 0; i < setContainer.size(); ++i) {
			str += setContainer.elementAt(i) + ",\t";
			if ((i + 1) % 5 == 0)
				str += "\n";
		}
		str += "\n+++++++++++++++++++++++++++++++++++++++++++++\n";
		return str;
	}

	/**
	 * Clear all set elements in setContainer
	 */
	public void clear() {
		for (int i = 0; i < this.setContainer.size(); ++i) {
			this.setContainer.clear();
		}
	}

	/**
	 * Check whether the set contains the given element
	 * 
	 * @param element, the element to be checked upon
	 * @return whether containing element is true or not
	 */
	public boolean contains(Object element) {

		for (int i = 0; i < this.setContainer.size(); ++i) {

			if (this.setContainer.elementAt(i).equals(element)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @returns this number of elements in the set
	 */
	public int cardinality() {

		return this.setContainer.size();
	}

	/**
	 * Add unique elements to set
	 * 
	 * @param element, the element to be inserted into object set
	 */
	public void add(Object element) {

		if (!this.setContainer.contains(element)) {// use the ! to show that we are adding unique elements
			this.setContainer.append(element);
		}

	}

	/**
	 * returns a new set = this set – B
	 * 
	 * @param B, the MySet object to perform loop on and check for elements to add
	 *           to setComp object
	 * @returns setComp as a new set
	 */
	public MySet complement(MySet B) {

		MySet setComp = new MySet();
		for (int i = 0; i < this.cardinality(); ++i) {
			if (!B.contains(this.setContainer.elementAt(i))) {
				setComp.add(i);
			}
		}
		return setComp;
	}

	/**
	 * @param B, the MySet object to iterate through to find union elements with this set
	 * @return MySet x variable, which holds union elements
	 */
	public MySet union(MySet B) {

		MySet x = new MySet();// MySet is brand new

		x.setContainer = this.setContainer.clone(); // x's set container is a clone of this.setContainer's MyArrayList

		for (int i = 0; i < B.cardinality(); ++i) {
			if (!x.contains(B.setContainer.elementAt(i))) {
				x.add(B.setContainer.elementAt(i));
			}
		}
		return x;
	}

	/**
	 * @param B, the MySet object to iterate through and find intersection 
	 * @return MySet variable x, which holds intersection points
	 */
	public MySet intersection(MySet B) {

		MySet x = new MySet();
		for (int i = 0; i < B.cardinality(); ++i) {// Go through size of MySet B
			// System.out.println(B.setContainer.elementAt(i));
			if (this.contains(B.setContainer.elementAt(i))) {// Add what is in both sets into X
				// System.out.println("Match");
				x.add(B.setContainer.elementAt(i));
			}
		}

		return x;
	}

	/**
	 * 
	 * @param B, check whether this set is a subset of set B  
	 * @return whether true or false
	 */
	public boolean subSetOf(MySet B) {

		for (int i = 0; i < this.cardinality(); ++i) {

			if (!B.contains(this.setContainer.elementAt(i))) {
				System.out.println(this.setContainer.elementAt(i));
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param B, MySet object, used to iterate through and compare with this set  
	 * @return returns a new set = (this set – B) U (B – this set)
	 */
	public MySet symmetricDifference(MySet B) {

		MySet newSet = new MySet();

		for (int i = 0; i < this.cardinality(); ++i) {
			if (!B.contains(this.setContainer.elementAt(i))) {
				newSet.add(this.setContainer.elementAt(i));
			}

		}
		for (int i = 0; i < B.cardinality(); ++i) {
			if (!this.contains(B.setContainer.elementAt(i))) {
				newSet.add(B.setContainer.elementAt(i));
			}
		}
		return newSet;
	}

}
