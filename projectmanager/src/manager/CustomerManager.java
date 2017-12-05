package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;

import entity.User;


public class CustomerManager {

	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	public void create(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

	public Customer get(int id) {
		return entityManager.find(Customer.class, id);
	}

	public List<Customer> getByName(String name) {
		String sql = "select * from customer where companyname like ";
		System.out.println(sql);
		return (List<Customer>) entityManager.createNativeQuery(sql + "'" + name + "'", Customer.class).getResultList();
	}
	public List<Customer> getCustomerByStatus() {
		String s1 = "SELECT p.id, p.projectname,c.companyname,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone, "+
						" p.startdate,p.enddate FROM projectmanager.project p INNER JOIN "+
						" projectmanager.customer c ON c.id = p.customer "+
						" WHERE (CURRENT_DATE() BETWEEN p.startdate AND p.enddate) > 0";	
		
		return (List<Customer>) entityManager.createNativeQuery(s1, Customer.class).getResultList();
	}

	public List<Customer> getactivecustomer() {

		String sql = "select c.id,c.companyname,c.companynumber,c.contact,c.email,c.phone "
				+ "as 'isActive' from projectmanager.customer c where( "
				+ "select count(p.id) from projectmanager.project p" + " where p.customer = c.id "
				+ " and(current_date() between p.startdate and p.enddate) > 0 )";

		return (List<Customer>) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	public List<Customer> getallcustomer() {

		String sql2 = "select c.id,c.companyname,c.companynumber,c.contact,c.email,c.phone,"
				+ "( select count(p.id) from projectmanager.project as p"
				+ " where p.customer = c.id  and (current_date() between p.startdate and p.enddate))"
				+ " >0 as 'isActive' from projectmanager.customer c ";

		return (List<Customer>) entityManager.createNativeQuery(sql2, Customer.class).getResultList();
	}
	

	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone, String username, String password) {

		User user1 = new User(username, password, "customer");
		user1 = ManagerHelper.getUserManager().createUser(user1);

		Customer customer = new Customer(companyname, companynumber, contactname, email, phone, user1);

		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		return customer;
	}

	public Reply deleteCustomer(int id) {
		Customer customer = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(customer);
			entityManager.getTransaction().commit();

			return new Reply();
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage() + id);

		}
		return Reply.DEFAULT_REPLY;

	}

	public Reply updateCustomer(int id, String companyname, String companynumber, String contactname, String email,String phone) {

		try {

			Customer customer = new Customer(id, companyname, companynumber, contactname, email, phone);
			entityManager.getTransaction().begin();

			entityManager.merge(customer);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply reply = new Reply();
			reply.setId(-1);
			reply.setMsg("try again");

			return reply.DEFAULT_REPLY;
		}

	}

}