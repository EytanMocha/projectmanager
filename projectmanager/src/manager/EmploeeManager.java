package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.User;

public class EmploeeManager {

	private final EntityManager entityManager;

	public EmploeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}

	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> getByName(String name) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Employee.class).getResultList();

	}

	public Employee createmployee(String firstname, String lastname, String email, String phone,String username,String password) {
		
		User user1 =new User(username,password,"emp");
		user1 = ManagerHelper.getUserManager().createUser(user1);
		
		Employee em = new Employee( firstname, lastname, email, phone,user1);
		
		entityManager.getTransaction().begin();
		entityManager.persist(em);
		entityManager.getTransaction();
		((EntityTransaction) entityManager).commit();
		return em;

	}

	public List<Employee> getallemployee() {
		String sql = "select * from employee ";
		return (List)entityManager.createNativeQuery(sql, Employee.class).getResultList();
		
		

	}public Reply updateEmployee(int id, String firstname, String lastname, String email, String phone
				) {
		
		try{
			
			Employee employee  = new Employee(id , firstname, lastname, email,  phone);
		entityManager.getTransaction().begin();	
		
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
		return new Reply();
		}catch (Exception e) {
		e.getMessage();
		Reply reply = new Reply();
		reply.setId(-1);
		reply.setMsg("try again");

		return reply.DEFAULT_REPLY;
	}

		
		
	}

	public Reply deleteEmployee(int id) {
		Employee employee = get(id);
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(employee);
				entityManager.getTransaction().commit();
				
				return new Reply();	
			} catch (Exception e) {
				e.getMessage();
				System.out.println(e.getMessage());
				
			}
			return Reply.DEFAULT_REPLY;
	
	}

	

}
