package AddressBook.bean;

/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
*/

/** This class is used by the AddressBean to sort the front-end table.
*|----------------------------------------------------------------------------|
*|                             CRC: SortableList                              |
*|----------------------------------------------------------------------------|
*|Abstract class used for sorting table on front-end              AddressBean |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely sorts table on front-end
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/


public abstract class SortableList {

    protected String sortColumnName;
    protected boolean ascending;

    // These are used to only resort if the order or column has changed.
    protected String oldSort;
    protected boolean oldAscending;


	/** Constructs SortableList object for sorting list data.
	 *@TheCs Cohesion - Constructs SortableList object for sorting list data.
	 * Completeness - Completely constructs SortableList object for sorting list data.
	 * Convenience - Simply constructs SortableList object for sorting list data.
	 * Clarity - It is simple to understand that this constructs SortableList object 
	 *           for sorting list data.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param The default sort column on startup              
	 */	    
    protected SortableList(String defaultSortColumn) {
        sortColumnName = defaultSortColumn;
        ascending = isDefaultAscending(defaultSortColumn);
        oldSort = sortColumnName;
        // make sure sortColumnName on first render
        oldAscending = !ascending;
    }

	/** Sorts the list.
	 *@TheCs Cohesion - Sorts the list.
	 * Completeness - Completely sorts the list.
	 * Convenience - Simply sorts the list.
	 * Clarity - It is simple to understand that this sorts the list.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.              
	 */	    
    protected abstract void sort();

	/** Return true if sortColumnName direction is ascending.
	 *@TheCs Cohesion - Return true if sortColumnName direction is ascending.
	 * Completeness - Completely return true if sortColumnName direction is ascending.
	 * Convenience - Simply return true if sortColumnName direction is ascending.
	 * Clarity - It is simple to understand that this return true if sortColumnName 
	 *           direction is ascending.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.              
	 */	     
    protected abstract boolean isDefaultAscending(String sortColumn);

	/** Gets the sortColumnName column.
	 *@TheCs Cohesion - Gets the sortColumnName column.
	 * Completeness - Completely gets the sortColumnName column.
	 * Convenience - Simply gets the sortColumnName column.
	 * Clarity - It is simple to understand that this gets the sortColumnName column.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return sortColumnName column to sort by                             
	 */	         
    public String getSortColumnName() {
        return sortColumnName;
    }

	/** Sets the sortColumnName column
	 *@TheCs Cohesion - Sets the sortColumnName column.
	 * Completeness - Completely sets the sortColumnName column.
	 * Convenience - Simply sets the sortColumnName column.
	 * Clarity - It is simple to understand that this sets the sortColumnName column.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
     * @param sortColumnName column to sort by                             
	 */	      
    public void setSortColumnName(String sortColumnName) {
        oldSort = this.sortColumnName;//Preserve the old sort by column
        this.sortColumnName = sortColumnName;

    }

	/** Returns whether sortColumnName is ascending or not.
	 *@TheCs Cohesion - Returns whether sortColumnName is ascending or not.
	 * Completeness - Completely returns whether sortColumnName is ascending or not.
	 * Convenience - Simply returns whether sortColumnName is ascending or not.
	 * Clarity - It is simple to understand that this returns whether sortColumnName
	 *           is ascending or not.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
     * @return true if the ascending sortColumnName otherwise false.                             
	 */	      
    public boolean isAscending() {
        return ascending;
    }

	/** Sets sortColumnName type.
	 *@TheCs Cohesion - Sets sortColumnName type.
	 * Completeness - Completely sets sortColumnName type.
	 * Convenience - Simply sets sortColumnName type.
	 * Clarity - It is simple to understand that this sets sortColumnName type.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
     * @param ascending true for ascending sortColumnName, false for descending sortColumnName.                             
	 */	    
    public void setAscending(boolean ascending) {
        oldAscending = this.ascending; //Preserve the old sort type
        this.ascending = ascending;
    }
}
