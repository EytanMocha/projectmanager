package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import entity.User;
import manager.ManagerHelper;
import manager.Reply;

@Path("/employee")
public class EmployeeService {
		
		public static EntityManagerFactory entityManagerFactory=
				Persistence.createEntityManagerFactory("projectmanager");
		
		public static EntityManager entityManager=
				entityManagerFactory.createEntityManager();
		
		@GET
		@Path("get")
		public Employee getEmployee(@QueryParam("id") int id){
			return ManagerHelper.getEmploeeManager().get(id); 
		}
		
		@GET
		@Path("getByName")
		public List<Employee> getByName(@QueryParam("firstname")String name){
			return ManagerHelper.getEmploeeManager().getByName(name);
		}
		@GET
		@Path("getallemployees")
		public List<Employee> getallemployee(){
			return  ManagerHelper.getEmploeeManager().getallemployee();
		}
		@GET
		@Path("create")
		public Employee create(@QueryParam("firstname")
		String firstname,@QueryParam("lastname") 
		String lastname,@QueryParam("email")
		String email,@QueryParam("phone") 
		String phone,@QueryParam("user") String username,@QueryParam("password") String password) {
			System.out.println("in the EmployeeCreate");
			return (Employee) ManagerHelper.getEmploeeManager().createmployee(firstname, lastname, email, phone, username, password);
		}
		@GET
		@Path("update")
		public Reply  updateEmployee(@QueryParam("id") 
		int id,@QueryParam("firstname")
		String firstname,@QueryParam("lastname") 
		String lastname,@QueryParam("email") 
		String email,@QueryParam("phone") 
		String phone) {
			System.out.println("update employee"+id);
			return (Reply)ManagerHelper.getEmploeeManager().updateEmployee(id, firstname, lastname, email, phone);
		}
		

		@GET
		@Path("deleteEmployee")
		public Reply  getById(@QueryParam("id")int id){
		
			return ManagerHelper.getEmploeeManager().deleteEmployee(id);
		}

}
