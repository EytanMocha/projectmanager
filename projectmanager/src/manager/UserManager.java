package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import javax.mail.MessagingException;

import entity.Customer;
import entity.Employee;
import entity.User;

public class UserManager {

	

	private final EntityManager entityManager;
	
	
	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}
	
	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}
	public User updateUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		return user;
	}
	
	public User createUser(User user1) {
		try{
			
			entityManager.getTransaction().begin();
			entityManager.persist(user1);
			entityManager.getTransaction().commit();
			
			return user1;
		}catch (Exception e) {
			return null;
		}
		
	}
	
	
	public void create(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}
	
	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
	
	public User get(Integer id) {
		return entityManager.find(User.class, id);
	}
	
	
	public User getByName(String name,String password) {
		try{	
		String sql = "select * from user where username like '"+name+"'"+" and password like "+password;
		System.out.println(sql);
		
			return (User) entityManager.createNativeQuery(sql, User.class).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public List<User> getallusers() {
		
		String sql = "select * from user";
		
		return (List<User>) entityManager.createNativeQuery(sql, User.class).getResultList();
		
	}
	public Reply deleteUser(int id) {
		User use = ManagerHelper.getUserManager().get(id);
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(use);
				entityManager.getTransaction().commit();
				
				return new Reply();	
			} catch (Exception e) {
				e.getMessage();
				System.out.println(e.getMessage());
				
			}
			return Reply.DEFAULT_REPLY;
	
	}
public User forgetPassword(String username) {
		
		String sql = "select * from projectmanager.user u "
				+ "where u.username ='"+username+"'";
		
		User user = (User)entityManager.createNativeQuery(sql, User.class).getSingleResult();
		
	     if(user.getType().equals("employee")){
			
			String sq = "select * from projectmanager.employee e "
					+ "inner join projectmanager.user u on e.user = u.id "
					+ "where u.username ='"+username+"'";

			Employee employee = (Employee)entityManager.createNativeQuery(sq, Employee.class).getSingleResult();
			
			try {
				MailHelper.sendMail(employee.getEmail(), "message subject: forget password",
						"message body text:"+user.getUser()+", password:"+user.getPassword());
				
				System.out.println("message send for employee");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for employee");
				
				return null;
			}
			
			
			
		}else if(user.getType().equals("customer")){
			String s = "select * from projectmanager.customer c "
					+ "inner join projectmanager.user u on c.user = u.id "
					+ "where u.username ='"+username+"'";
			
			Customer customer = (Customer)entityManager.createNativeQuery(s, Customer.class).getSingleResult();
			
			try {
				MailHelper.sendMail(customer.getEmail(), "message subject: forget password",
						"message body text:"+user.getUser()+", password:"+user.getPassword());
				
				System.out.println("message send for customer");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for customer");
			}

		}else{
			try {
				MailHelper.sendMail("smoken03@gmail.com", "message subject: forget password",
					"message body text: username:"+user.getUser()+", password:"+user.getPassword());
				
				System.out.println("message send for manager");
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("message Exception for manager");
			}
		}
		return new User();
	}
}