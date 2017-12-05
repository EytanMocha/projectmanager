package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.User;
import manager.ManagerHelper;
import manager.Reply;

@Path("/customer")
public class CustomerService {
	
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("projectmanager");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	
	@GET
	@Path("get")
	public Customer getCustomer(@QueryParam("id") int id){
		return ManagerHelper.getCustomerManager().get(id); 
	}
	
	@GET
	@Path("getByName")
	public List<Customer> getByName(@QueryParam("companyname")String name){
		return ManagerHelper.getCustomerManager().getByName(name);
	}
	
	@GET
	@Path("getallcustomer")
	public List<Customer> getallcustomer(){
		return (List<Customer>) ManagerHelper.getCustomerManager().getallcustomer();
	}
	@GET
	@Path("getCustomerByStatus")
	public List<Customer> getCustomerByStatus(){
		return (List<Customer>) ManagerHelper.getCustomerManager().getCustomerByStatus();
	}
	@GET
	@Path("getactivecustomer")
	public List<Customer> getactivecustomer(){
		return (List<Customer>)ManagerHelper.getCustomerManager().getactivecustomer();
	}
	
	@GET
	@Path("create")
	public Customer create(@QueryParam("companyname") 
	String companyname,@QueryParam("companynumber") 
	String companynumber,@QueryParam("contactname")
	String contactname,@QueryParam("email")
	String email,@QueryParam("phone") 
	String phone, @QueryParam("username") String username, @QueryParam("password") String password) {
		return (Customer)ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, contactname, email, phone,  username, password);
	}
	@GET
	@Path("update")
	public Reply update(@QueryParam("id") int id,@QueryParam("companyname")
	String companyname,@QueryParam("companynumber") String companynumber,@QueryParam("contactname")
	String contactname,@QueryParam("email") String email,@QueryParam("phone") String phone) {
		return (Reply)ManagerHelper.getCustomerManager().updateCustomer(id , companyname, companynumber, contactname, email, phone);
	}
	@GET
	@Path("deleteCustomer")
	public Reply deleteCustomer(@QueryParam("id")int id) {
		return ManagerHelper.getCustomerManager().deleteCustomer(id );
	}
	
	

}
