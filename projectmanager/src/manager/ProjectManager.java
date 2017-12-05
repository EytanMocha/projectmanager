package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Project;

public class ProjectManager {
	
	
private final EntityManager entityManager;
	
	
	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}
	
	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}
	
	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}
	
	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}
	
	public List<Project> getByName(String name) {
		String sql = "select * from project where projectname like '";
		
		return (List)entityManager.createNativeQuery(sql+name+"'", Project.class).getSingleResult();
	}


public List<Project> getProjectsByStatus() {
	String q = "SELECT * FROM projectmanager.project p "
			+ "WHERE (CURRENT_DATE() BETWEEN p.startdate AND p.enddate)";
		
		return (List<Project>)entityManager.createNativeQuery(q, Project.class).getResultList();
	}
	
	public List<Project> projectCount(String name) {
		String sql = "select * from project where projectname like '";
		
		return (List<Project>)entityManager.createNativeQuery(sql+name+"'", Project.class).getResultList();
	}
	
	public List<Project> getAllprojects() {
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone"+
	" from projectmanager.project p";
              
				
				
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
		
	}
	/*activ projects*/
	public List<Project> getActiveProjectCustomer(int userId) {
		String sql = "SELECT* FROM projectmanager.project p "
				+ "inner join projectmanager.customer c on p.customer= c.id "
				+ "inner join projectmanager.users u on c.user = u.id "
				+ " where  p.enddate >= current_date() and u.id ="+userId;
			return (List<Project>)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	/*near end projects*/
	public List<Project> getNearEndProject() {
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone"+
	" from projectmanager.project p where  p.enddate  between current_date() and current_date()+90";
				
				
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
		
	}
	/*customer projects*/
public List<Project> getCustomerProjectsByUser(int userid) {
		
		String sql2 = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone "
				+"from projectmanager.project p "
				+"inner join customer c on c.id=p.customer "
				+"inner join user u on u.id = c.user "
				+"where u.id="+userid;
		
		return (List<Project>) entityManager.createNativeQuery(sql2,Project .class).getResultList();
	}
	
	public String update(int id,String projectname,int customer,String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone,String startdate,String  enddate) {
		Customer cust=ManagerHelper.getCustomerManager().get(customer);
		Reply reply = new Reply(); 
		try{
		entityManager.getTransaction().begin();
		System.out.println("update"+id);
		Project project  = new Project(id ,projectname, cust, customerprojectmanager, projectmanageremail,  projectmanagerephone, startdate,enddate);
		entityManager.merge(project);
		entityManager.getTransaction().commit();
		return reply.getMsg();
		}catch (Exception e) {
			reply.setMsg("update fail");
		
		return reply.getMsg();
		}
	}

	public void createPro(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}
	public Project create(String projectname,int customer,String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone,String startdate,String  enddate) {
		Customer cust=ManagerHelper.getCustomerManager().get(customer);
		entityManager.getTransaction().begin();
		Project project = new Project(projectname, cust, customerprojectmanager, projectmanageremail,  projectmanagerephone, startdate,enddate);
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		return project ;
	}
	
	public Reply deleteProject(int id) {
		Project project = get(id);
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(project);
				entityManager.getTransaction().commit();
				
				return new Reply();	
			} catch (Exception e) {
				e.getMessage();
				System.out.println(e.getMessage());
				
			}
			return Reply.DEFAULT_REPLY;
	
	}
}



