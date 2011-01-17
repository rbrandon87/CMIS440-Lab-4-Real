package myPersistence;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import AddressBook.bean.Logger;


/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
*/

/** This class is used to save,delete,update,find Addresses objects
* A data access object (DAO) providing persistence and search support for
* Addresses entities. Transaction control of the save(), update() and delete()
* operations must be handled externally by senders of these methods or must be
* manually added to each of these methods for data to be persisted to the JPA
* datastore. 
*|----------------------------------------------------------------------------|
*|                             CRC: AddressesDAO                              |
*|----------------------------------------------------------------------------|
*|Used to save, delete, update, and find Addresses objects       AddressBean  |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely used to save,delete,update,find Addresses objects.
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/

public class AddressesDAO {
	// property constants
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String STREET = "street";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP = "zip";
	public static final String EMAILADDRESS = "emailaddress";
	public static final String PHONENUMBER = "phonenumber";
	Logger myLog = new Logger();
	
	/** Constructs AddressesDAO object.
	 *@TheCs Cohesion - Constructs AddressesDAO object.
	 * Completeness - Completely constructs AddressesDAO object.
	 * Convenience - Simply constructs AddressesDAO object.
	 * Clarity - It is simple to understand that this constructs AddressesDAO
	 *           object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.            
	 */	 	
	public AddressesDAO(){
		
	}

	/** Retrieves the EntityManager.
	 *@TheCs Cohesion - Retrieves the EntityManager.
	 * Completeness - Completely retrieves the EntityManager.
	 * Convenience - Simply retrieves the EntityManager.
	 * Clarity - It is simple to understand that this retrieves the EntityManager.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return EntityManager Needed for use of Addresses object                           
	 */	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/** Saves Addresses Entity to database.
	 *@TheCs Cohesion - Saves Addresses Entity to database.
	 * Completeness - Completely saves Addresses Entity to database.
	 * Convenience - Simply saves Addresses Entity to database.
	 * Clarity - It is simple to understand that this saves Addresses Entity
	 *           to database.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 *               
	 * Perform an initial save of a previously unsaved Addresses entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AddressesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity Addresses entity to persist
	 * @throws RuntimeException when the operation fails
	 */
	public void save(Addresses entity) {
		myLog.log("saving Addresses instance - " + Level.INFO);
		try {
			getEntityManager().persist(entity);
			myLog.log("save successful - " + Level.INFO);
		} catch (RuntimeException re) {
			myLog.log("save failed - " + re + " - " + Level.SEVERE);
			throw re;
		}
	}

	/** Deletes Addresses Entity from database.
	 *@TheCs Cohesion - Deletes Addresses Entity from database.
	 * Completeness - Completely deletes Addresses Entity from database.
	 * Convenience - Simply deletes Addresses Entity from database.
	 * Clarity - It is simple to understand that this deletes Addresses Entity
	 *           from database.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 *    
	 * Delete a persistent Addresses entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AddressesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity Addresses entity to delete
	 * @throws RuntimeException when the operation fails
	 */
	public void delete(Addresses entity) {
		myLog.log("deleting Addresses instance - " + Level.INFO);
		try {
			entity = getEntityManager().getReference(Addresses.class,
					entity.getAddressid());
			getEntityManager().remove(entity);
			myLog.log("delete successful - " + Level.INFO);
		} catch (RuntimeException re) {
			myLog.log("delete failed - " + re + " - " + Level.SEVERE);
			throw re;
		}
	}

	/** Updates Addresses Entity in database.
	 *@TheCs Cohesion - Updates Addresses Entity in database.
	 * Completeness - Completely updates Addresses Entity in database.
	 * Convenience - Simply updates Addresses Entity in database.
	 * Clarity - It is simple to understand that this updates Addresses Entity
	 *           in database.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 *
	 * Persist a previously saved Addresses entity and return it or a copy of it
	 * to the sender. A copy of the Addresses entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AddressesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity Addresses entity to update
	 * @return Addresses the persisted Addresses entity instance, may not be the
	 *         same
	 * @throws RuntimeException if the operation fails
	 */
	public Addresses update(Addresses entity) {
		myLog.log("updating Addresses instance - " + Level.INFO);
		try {
			Addresses result = getEntityManager().merge(entity);
			myLog.log("update successful - " + Level.INFO);
			return result;
		} catch (RuntimeException re) {
			myLog.log("update failed - " + re + " - " + Level.SEVERE);
			throw re;
		}
	}

	/** Find Addresses Entity in database by ID.
	 *@TheCs Cohesion - Find Addresses Entity in database by ID.
	 * Completeness - Completely find Addresses Entity in database by ID.
	 * Convenience - Simply find Addresses Entity in database by ID.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by ID.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param id unique id of Addresses entity to find
	 * @return Addresses entity found
	 * @throws RuntimeException if the operation fails
	 */	
	public Addresses findById(Long id) {
		myLog.log("finding Addresses instance with id: " + id +
				Level.INFO);
		try {
			Addresses instance = getEntityManager().find(Addresses.class, id);
			return instance;
		} catch (RuntimeException re) {
			myLog.log("find failed - " + re + " - " + Level.SEVERE);
			throw re;
		}
	}

	/** Find all Addresses entities with a specific property value.
	 *@TheCs Cohesion - Find all Addresses entities with a specific property
	 *                  value.
	 * Completeness - Completely find all Addresses entities with a specific
	 *                property value.
	 * Convenience - Simply find all Addresses entities with a specific 
	 *               property value.
	 * Clarity - It is simple to understand that this finds all Addresses
	 *           entities with a specific property value.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * 
	 * @param propertyName the name of the Addresses property to query
	 * @param value the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Addresses> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		myLog.log("finding Addresses instance with property: "
				+ propertyName + ", value: " + value + Level.INFO);
		try {
			/**
			 * I added the 'lower()' tag to the SQL statement below to make this a case 
			 * insensitive search.
			 */
			final String queryString = "select model from Addresses model where lower(model."
					+ propertyName + ")= lower(:propertyValue)";
			/**
			 * Create the query on the entity manager and set the parameters.
			 */
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			/**
			 * Everything below is using a optional parameter where you can
			 * set the max results to return.
			 */
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			myLog.log("find by property name failed" + re + " - " + Level.SEVERE);
			throw re;
		}
	}

	/** Find Addresses Entity in database by first name.
	 *@TheCs Cohesion - Find Addresses Entity in database by first name.
	 * Completeness - Completely find Addresses Entity in database by first name.
	 * Convenience - Simply find Addresses Entity in database by first name.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by first name.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param firstname first name of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */		
	public List<Addresses> findByFirstname(Object firstname,
			int... rowStartIdxAndCount) {
		return findByProperty(FIRSTNAME, firstname, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by last name.
	 *@TheCs Cohesion - Find Addresses Entity in database by last name.
	 * Completeness - Completely find Addresses Entity in database by last name.
	 * Convenience - Simply find Addresses Entity in database by last name.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by last name.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param lastname last name of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByLastname(Object lastname,
			int... rowStartIdxAndCount) {
		return findByProperty(LASTNAME, lastname, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by street.
	 *@TheCs Cohesion - Find Addresses Entity in database by street.
	 * Completeness - Completely find Addresses Entity in database by street.
	 * Convenience - Simply find Addresses Entity in database by street.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by street.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param street street of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByStreet(Object street,
			int... rowStartIdxAndCount) {
		return findByProperty(STREET, street, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by city.
	 *@TheCs Cohesion - Find Addresses Entity in database by city.
	 * Completeness - Completely find Addresses Entity in database by city.
	 * Convenience - Simply find Addresses Entity in database by city.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by city.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param city city of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByCity(Object city, int... rowStartIdxAndCount) {
		return findByProperty(CITY, city, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by state.
	 *@TheCs Cohesion - Find Addresses Entity in database by state.
	 * Completeness - Completely find Addresses Entity in database by state.
	 * Convenience - Simply find Addresses Entity in database by state.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by state.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param state state name of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByState(Object state, int... rowStartIdxAndCount) {
		return findByProperty(STATE, state, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by zip code.
	 *@TheCs Cohesion - Find Addresses Entity in database by zip code.
	 * Completeness - Completely find Addresses Entity in database by zip code.
	 * Convenience - Simply find Addresses Entity in database by zip code.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by zip code.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param zip zip code of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByZip(Object zip, int... rowStartIdxAndCount) {
		return findByProperty(ZIP, zip, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by email address.
	 *@TheCs Cohesion - Find Addresses Entity in database by email address.
	 * Completeness - Completely find Addresses Entity in database by email address.
	 * Convenience - Simply find Addresses Entity in database by email address.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by email address.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param emailaddress email address of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByEmailaddress(Object emailaddress,
			int... rowStartIdxAndCount) {
		return findByProperty(EMAILADDRESS, emailaddress, rowStartIdxAndCount);
	}

	/** Find Addresses Entity in database by phone number.
	 *@TheCs Cohesion - Find Addresses Entity in database by phone number.
	 * Completeness - Completely find Addresses Entity in database by phone number.
	 * Convenience - Simply find Addresses Entity in database by phone number.
	 * Clarity - It is simple to understand that this find Addresses Entity
	 *           in database by phone number.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param phonenumber phone number of Addresses entity to find
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Addresses> found by query
	 */			
	public List<Addresses> findByPhonenumber(Object phonenumber,
			int... rowStartIdxAndCount) {
		return findByProperty(PHONENUMBER, phonenumber, rowStartIdxAndCount);
	}

	/** Find all Addresses entities.
	 *@TheCs Cohesion - Find all Addresses entities.
	 * Completeness - Completely find all Addresses entities.
	 * Convenience - Simply find all Addresses entities.
	 * Clarity - It is simple to understand that this finds all Addresses
	 *           entities.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Addresses> all Addresses entities
	 */
	@SuppressWarnings("unchecked")
	public List<Addresses> findAll(final int... rowStartIdxAndCount) {
		myLog.log("finding all Addresses instances - " + Level.INFO);
		try {
			final String queryString = "select model from Addresses model";
			/**
			 * Create the query on the entity manager.
			 */
			Query query = getEntityManager().createQuery(queryString);
			/**
			 * Everything below is using a optional parameter where you can
			 * set the max results to return.
			 */			
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			myLog.log("find all failed - " + re + " - " + Level.SEVERE);
			throw re;
		}
	}

}