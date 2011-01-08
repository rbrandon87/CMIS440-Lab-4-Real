package AddressBook.bean;

/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
* IDE: MyEclipse 9.0 M1
* OS: Windows 7
* Java: Java EE 5, JSF 1.2, JPA 2.0, IceFaces 1.8.1, GlassFish 2.1.1
* Files: 
*
* Program Requirements: 
*
* Program Design: 
*
* Things you what me to know before I grade your work: 
*/





import java.util.*;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import mypersistance.Addresses;
import mypersistance.AddressesDAO;
import mypersistance.EntityManagerHelper;


/**
 * @author Bwiz_Dual_PC
 *
 */
public class AddressBean extends SortableList{

	AddressesDAO myAddressesDAO = new AddressesDAO();
	Addresses myTableAddress;
	Addresses myNewAddress;
	Addresses mySearchAddress;
	List<Addresses> myAddresses = new ArrayList<Addresses>();
	List<SelectItem> lastNames;
	Logger myLog = new Logger();
	boolean visible = false;
	
    

	

	public AddressBean() {
		super("ID");
		myTableAddress = new Addresses();
		myNewAddress = new Addresses();
		mySearchAddress = new Addresses();
		myAddresses = myAddressesDAO.findAll();
	}
	
	public Addresses getCurrentTableAddress(){
		return myTableAddress;
	}
	
	public void setCurrentTableAddress(Addresses tableAddress){
		myTableAddress = tableAddress;
	}
	
	public Addresses getCurrentNewAddress(){
		return myNewAddress;
	}
	
	public void setCurrentNewAddress(Addresses newAddress){
		myNewAddress = newAddress;
	}	
	
	public Addresses getCurrentSearchAddress(){
		return mySearchAddress;
	}
	
	public void setCurrentSearchAddress(Addresses searchAddress){
		mySearchAddress = searchAddress;
	}	
	
    public void setAddressDao(AddressesDAO addressesDao) {
        myAddressesDAO = addressesDao;
    }
	 
	
	public String addAddress(){
		try{
			if (myNewAddress.getCity().equals("") || myNewAddress.getEmailaddress().equals("") || myNewAddress.getFirstname().equals("") || 
					myNewAddress.getLastname().equals("") || myNewAddress.getPhonenumber().equals("") || 
					myNewAddress.getState().equals("") || myNewAddress.getStreet().equals("") || myNewAddress.getZip().equals("")){
				return "failure";
			}
			EntityManagerHelper.beginTransaction();
			myAddressesDAO.save(myNewAddress);
			EntityManagerHelper.commit();
			myLog.log("ID: " + myNewAddress.getAddressid() + " Successfully added into database");
			myNewAddress = new Addresses();
			myTableAddress.setEditable(false);
			myTableAddress = new Addresses();
			myAddresses = new AddressesDAO().findAll();
			return "success";
			}catch (Exception exception){
				myLog.log(exception.getMessage());
				return "failure";
			}
	}
	
	public void updateAddress(ActionEvent evt){
		try{
			if (myTableAddress.getCity().equals("") || myTableAddress.getEmailaddress().equals("") || myTableAddress.getFirstname().equals("") || 
					myTableAddress.getLastname().equals("") || myTableAddress.getPhonenumber().equals("") || 
					myTableAddress.getState().equals("") || myTableAddress.getStreet().equals("") || myTableAddress.getZip().equals("")){
				return;
			}				
			myTableAddress.setEditable(false);		
			EntityManagerHelper.beginTransaction();
			myAddressesDAO.update(myTableAddress);
			EntityManagerHelper.commit();
			myLog.log("ID: " + myTableAddress.getAddressid() + " Successfully updated in database");
			myTableAddress = new Addresses();
			
		}catch (Exception exception){
			myLog.log(exception.getMessage());
		}		
	}
	
	public void deleteAddress(ActionEvent evt){
		try{
					
			EntityManagerHelper.beginTransaction();
			myAddressesDAO.delete(myTableAddress);
			EntityManagerHelper.commit();
			myLog.log("ID: " + myTableAddress.getAddressid() + " Successfully deleted from database");
			myTableAddress.setEditable(false);
			myTableAddress = new Addresses();
			myTableAddress.setEditable(false);	
			myAddresses = new AddressesDAO().findAll();
		}catch (Exception exception){
			myLog.log(exception.getMessage());
		}		
	}		
	
	
	public List<Addresses> getAllAddresses(){

		try{
			if ( mySearchAddress.getLastname() == null || mySearchAddress.getLastname().equals("")){
				//myAddresses = new AddressesDAO().findAll();
			}else{
				myAddresses =  new AddressesDAO().findByLastname(mySearchAddress.getLastname());
			}
			if (!oldSort.equals(sortColumnName) ||
					oldAscending != ascending){
					sort();
					oldSort = sortColumnName;
					oldAscending = ascending;
			}
			return myAddresses;
        }catch (Exception exception){
        	myLog.log(exception.getMessage());
        	return myAddresses;
        }
        
	}
	
	public void getAddressesByLastName(ValueChangeEvent event){
		try{			
			lastNames = new ArrayList<SelectItem>();
			myAddresses = new AddressesDAO().findAll();
			Object searchWord = event.getNewValue();
			for(int i = 0; i < myAddresses.size(); i++){
				if (myAddresses.get(i).getLastname().length() == 0){
					
				}else if (myAddresses.get(i).getLastname().length() < searchWord.toString().length()){
				
				}else if(myAddresses.get(i).getLastname().substring(0,searchWord.toString().length()).equalsIgnoreCase(searchWord.toString())){
					if (!lastNames.contains(myAddresses.get(i).getLastname())){
						lastNames.add(new SelectItem(myAddresses.get(i).getLastname(),myAddresses.get(i).getLastname()));
					}
					
				}
			}
        }catch (Exception exception){
        	myLog.log(exception.getMessage());
        }		
		
	}
	
	public List<SelectItem> getLastNameMatches(){
		return lastNames;
	}
	
	public void cancel(ActionEvent evt){
		myNewAddress = new Addresses();
		mySearchAddress = new Addresses();
		myAddresses = myAddressesDAO.findAll();
		myTableAddress.setEditable(false);
		myTableAddress = new Addresses();
	}
	
	public void cancelEdit(ActionEvent evt){
		myTableAddress.setEditable(false);
		myTableAddress = new Addresses();
		
	}
	
	public void editAction(ActionEvent evt) {
		try{
	    
			if (myTableAddress.getAddressid()!= null){
				return;
			}		
		
			myTableAddress = myAddressesDAO.findById((Long) evt.getComponent().getAttributes().get("theAddressID"));
			myTableAddress.setEditable(true);
		
		}catch (Exception exception){
        	myLog.log(exception.getMessage());
        }
	}
	
    public void validateEmail(FacesContext context, UIComponent validate, Object value){
    	try{
    
    		String email = (String)value;

    		if(email.indexOf('@')==-1){
    			((UIInput)validate).setValid(false);
    			FacesMessage msg = new FacesMessage("Invalid Email entered");
    			context.addMessage(validate.getClientId(context), msg);
    		}
        
        }catch (Exception exception){
        	myLog.log(exception.getMessage());
        }        
    }
    
    public void validatePhone(FacesContext context, UIComponent validate, Object value){
    	try{
    		String phone = (String)value;

    		if(phone.equals("")){
    			((UIInput)validate).setValid(false);
    			FacesMessage msg = new FacesMessage("Phone Number is required");
    			context.addMessage(validate.getClientId(context), msg);
    		}
        
        }catch (Exception exception){
        	myLog.log(exception.getMessage() + "\n");
        }        
    }
    
    public void validateAllOthers(FacesContext context, UIComponent validate, Object value){
    	try{
    
    		String allOther = (String)value;

    		if(allOther.equals("")){
    			((UIInput)validate).setValid(false);
    			FacesMessage msg = new FacesMessage("Everything with an asterisk* is required!");
    			context.addMessage(validate.getClientId(context), msg);
    		}
        
        }catch (Exception exception){
        	myLog.log(exception.getMessage());
        }        
    }    
    

    
    
    /**
     * Determines the sortColumnName order.
     *
     * @param   sortColumn to sortColumnName by.
     * @return  whether sortColumnName order is ascending or descending.
     */
    protected boolean isDefaultAscending(String sortColumn) {
        return true;
    }

    /**
     *  Sorts the list of car data.
     */
    protected void sort() {
    	try{
    		Comparator<Object> comparator = new Comparator<Object>() {
    			public int compare(Object o1, Object o2) {
    				Addresses c1 = (Addresses) o1;
    				Addresses c2 = (Addresses) o2;
    				if (sortColumnName == null) {
    					return 0;
    				}
    				if (sortColumnName.equals("ID")) {
    					return ascending ?
    							new Long(c1.getAddressid()).compareTo(new Long(c2.getAddressid())) :
    							new Long(c2.getAddressid()).compareTo(new Long(c1.getAddressid()));
    				} else if (sortColumnName.equals("First Name")) {
    					return ascending ? c1.getFirstname().compareTo(c2.getFirstname()) :
    						c2.getFirstname().compareTo(c1.getFirstname());
    				} else if (sortColumnName.equals("Last Name")) {
    					return ascending ? c1.getLastname().compareTo(c2.getLastname()) :
                            c2.getLastname().compareTo(c1.getLastname());
    				} else if (sortColumnName.equals("Street")) {
    					return ascending ?
                            c1.getStreet().compareTo(c2.getStreet()) :
                            c2.getStreet().compareTo(c1.getStreet());
    				} else if (sortColumnName.equals("City")) {
    					return ascending ?
                            c1.getCity().compareTo(c2.getCity()) :
                            c2.getCity().compareTo(c1.getCity());
    				}  else if (sortColumnName.equals("State")) {
    					return ascending ?
                            c1.getState().compareTo(c2.getState()) :
                            c2.getState().compareTo(c1.getState());
    				} else if (sortColumnName.equals("Zip Code")) {
    					return ascending ?
                            c1.getZip().compareTo(c2.getZip()) :
                            c2.getZip().compareTo(c1.getZip());
    				} else if (sortColumnName.equals("Email Address")) {
    					return ascending ?
                            c1.getEmailaddress().compareTo(c2.getEmailaddress()) :
                            c2.getEmailaddress().compareTo(c1.getEmailaddress());
    				} else if (sortColumnName.equals("Phone Number")) {
    					return ascending ?
                            c1.getPhonenumber().compareTo(c2.getPhonenumber()) :
                            c2.getPhonenumber().compareTo(c1.getPhonenumber());
    				} else return 0;
    				}
    			};
    			Collections.sort(myAddresses, comparator);
        
        }catch (Exception exception){
        	myLog.log(exception.getMessage());
        }        
        
    }
    

	
    public String closePopup() {
        visible = false;
        return "success";
    }
    
    public String openPopup() {
        visible = true;
        return "success";
    }
    
    public boolean getPopupVisible(){
    	return visible;
    }

	

	


}

















