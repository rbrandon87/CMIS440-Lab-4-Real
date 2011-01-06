package mypersistance;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Addresses entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see mypersistance.Addresses
 * @author MyEclipse Persistence Tools
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

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
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
	 * @param entity
	 *            Addresses entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Addresses entity) {
		EntityManagerHelper.log("saving Addresses instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
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
	 * @param entity
	 *            Addresses entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Addresses entity) {
		EntityManagerHelper
				.log("deleting Addresses instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Addresses.class,
					entity.getAddressid());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
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
	 * @param entity
	 *            Addresses entity to update
	 * @return Addresses the persisted Addresses entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Addresses update(Addresses entity) {
		EntityManagerHelper
				.log("updating Addresses instance", Level.INFO, null);
		try {
			Addresses result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Addresses findById(Long id) {
		EntityManagerHelper.log("finding Addresses instance with id: " + id,
				Level.INFO, null);
		try {
			Addresses instance = getEntityManager().find(Addresses.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Addresses entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Addresses property to query
	 * @param value
	 *            the property value to match
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
		EntityManagerHelper.log("finding Addresses instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Addresses model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
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
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Addresses> findByFirstname(Object firstname,
			int... rowStartIdxAndCount) {
		return findByProperty(FIRSTNAME, firstname, rowStartIdxAndCount);
	}

	public List<Addresses> findByLastname(Object lastname,
			int... rowStartIdxAndCount) {
		return findByProperty(LASTNAME, lastname, rowStartIdxAndCount);
	}

	public List<Addresses> findByStreet(Object street,
			int... rowStartIdxAndCount) {
		return findByProperty(STREET, street, rowStartIdxAndCount);
	}

	public List<Addresses> findByCity(Object city, int... rowStartIdxAndCount) {
		return findByProperty(CITY, city, rowStartIdxAndCount);
	}

	public List<Addresses> findByState(Object state, int... rowStartIdxAndCount) {
		return findByProperty(STATE, state, rowStartIdxAndCount);
	}

	public List<Addresses> findByZip(Object zip, int... rowStartIdxAndCount) {
		return findByProperty(ZIP, zip, rowStartIdxAndCount);
	}

	public List<Addresses> findByEmailaddress(Object emailaddress,
			int... rowStartIdxAndCount) {
		return findByProperty(EMAILADDRESS, emailaddress, rowStartIdxAndCount);
	}

	public List<Addresses> findByPhonenumber(Object phonenumber,
			int... rowStartIdxAndCount) {
		return findByProperty(PHONENUMBER, phonenumber, rowStartIdxAndCount);
	}

	/**
	 * Find all Addresses entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Addresses> all Addresses entities
	 */
	@SuppressWarnings("unchecked")
	public List<Addresses> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Addresses instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Addresses model";
			Query query = getEntityManager().createQuery(queryString);
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
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}