package myPersistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

public class EntityManagerHelper {
	
	private static final EntityManagerFactory emf; 
	private static final ThreadLocal<EntityManager> threadLocal;
	private static final Logger logger;
	
	static {
		emf = Persistence.createEntityManagerFactory("AddressBookPU"); 		
		threadLocal = new ThreadLocal<EntityManager>();
		logger = Logger.getLogger("AddressBookPU");
		logger.setLevel(Level.ALL);
	}

	/** Constructs AddressesDAO object.
	 *@TheCs Cohesion - Constructs AddressesDAO object.
	 * Completeness - Completely constructs AddressesDAO object.
	 * Convenience - Simply constructs AddressesDAO object.
	 * Clarity - It is simple to understand that this constructs AddressesDAO
	 *           object.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.            
	 */		
	public EntityManagerHelper(){
		
	}
	
	/** Retrieves EntityManager from local thread.
	 *@TheCs Cohesion - Retrieves EntityManager from local thread.
	 * Completeness - Completely retrieves EntityManager from local thread.
	 * Convenience - Simply retrieves EntityManager from local thread.
	 * Clarity - It is simple to understand that this retrieves EntityManager
	 *           from local thread.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return manager EntityManager from local thread                           
	 */			
	public static EntityManager getEntityManager() {
		/**
		 * Get the entity manager on the local thread and if it is null
		 * or not open then create another one from the entity manager
		 * factory and set it to the local thread and then return it.
		 */
		EntityManager manager = threadLocal.get();		
		if (manager == null || !manager.isOpen()) {
			manager = emf.createEntityManager();
			threadLocal.set(manager);
		}
		return manager;
	}
	
	/** Closes current EntityManager and local thread.
	 *@TheCs Cohesion - Closes current EntityManager and local thread.
	 * Completeness - Completely closes current EntityManager and local thread.
	 * Convenience - Simply closes current EntityManager and local thread.
	 * Clarity - It is simple to understand that this closes current EntityManager
	 *           and local thread.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.                           
	 */				
	 public static void closeEntityManager() {
		 /**
		  * Get the entity manager on the local thread, get rid of the 
		  * local thread, and if not null close the entity manager. 
		  */
		 EntityManager em = threadLocal.get();
         threadLocal.set(null);
         if (em != null) em.close();
    }
    
	/** Starts a transaction on the entity manager.
	*@TheCs Cohesion - Starts a transaction on the entity manager.
	* Completeness - Completely starts a transaction on the entity manager.
	* Convenience - Simply starts a transaction on the entity manager.
	* Clarity - It is simple to understand that this starts a transaction
	*           on the entity manager.
	* Consistency - It uses the same syntax rules as the rest of the class and
	*               continues to use proper casing and indentation.                           
	*/	 
    public static void beginTransaction() {
    	getEntityManager().getTransaction().begin();
    }

	/** Commits a transaction on the entity manager.
	*@TheCs Cohesion - Commits a transaction on the entity manager.
	* Completeness - Completely commits a transaction on the entity manager.
	* Convenience - Simply commits a transaction on the entity manager.
	* Clarity - It is simple to understand that this commits a transaction
	*           on the entity manager.
	* Consistency - It uses the same syntax rules as the rest of the class and
	*               continues to use proper casing and indentation.                           
	*/	     
    public static void commit() {
    	getEntityManager().getTransaction().commit();
    }  

	/** Rollbacks a transaction on the entity manager.
	*@TheCs Cohesion - Rollbacks a transaction on the entity manager.
	* Completeness - Completely rollbacks a transaction on the entity manager.
	* Convenience - Simply rollbacks a transaction on the entity manager.
	* Clarity - It is simple to understand that this rollbacks a transaction
	*           on the entity manager.
	* Consistency - It uses the same syntax rules as the rest of the class and
	*               continues to use proper casing and indentation.                           
	*/	         
    public static void rollback() {
    	getEntityManager().getTransaction().rollback();
    } 
    
	/** Creates a query on the entity manager.
	*@TheCs Cohesion - Creates a query on the entity manager.
	* Completeness - Completely Creates a query on the entity manager.
	* Convenience - Simply creates a query on the entity manager.
	* Clarity - It is simple to understand that this creates a query on
	*           the entity manager.
	* Consistency - It uses the same syntax rules as the rest of the class and
	*               continues to use proper casing and indentation.
	* @param query query string to be created
	* @return created query on entity manager                                         
	*/	         
    public static Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}
	
	/** Logs messages to entity manager logger.
	*@TheCs Cohesion - Logs messages to entity manager logger.
	* Completeness - Completely logs messages to entity manager logger.
	* Convenience - Simply logs messages to entity manager logger.
	* Clarity - It is simple to understand that this logs messages to 
	*           entity manager logger.
	* Consistency - It uses the same syntax rules as the rest of the class and
	*               continues to use proper casing and indentation.
	* @param info message to be logged
	* @param level severity of the message
	* @param ex the exception thrown, if any.                                          
	*/	         
	public static void log(String info, Level level, Throwable ex) {
    	logger.log(level, info, ex);
    }
    
}
