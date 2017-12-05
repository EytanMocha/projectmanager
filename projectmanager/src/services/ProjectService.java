package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Project;
import manager.ManagerHelper;
import manager.Reply;

@Path("/project")
public class ProjectService {
	
	public static EntityManagerFactory entityManagerFactory=
			Persistence.createEntityManagerFactory("projectmanager");
	
	public static EntityManager entityManager=
			entityManagerFactory.createEntityManager();
	
	@GET
	@Path("get")
	public Project getProject(@QueryParam("id") int id){
		return ManagerHelper.getProjectManager().get(id); 
	}
	@GET
	@Path("getProjectsUser")
	public List< Project> getCustomerProjectsByUser(@QueryParam("userid") int userid){
		return ( List <Project>) ManagerHelper.getProjectManager().getCustomerProjectsByUser(userid); 
	}
	
	@GET
	@Path("getByName")
	public List<Project> getByName(@QueryParam("projectname")String name){
		return ManagerHelper.getProjectManager().getByName(name);
	}

	@GET
	@Path("getAllProjects")
	public List<Project> getAllProjects(){
		return ManagerHelper.getProjectManager().getAllprojects();
	}
	@GET
	@Path("getProjectsByStatus")
	public List<Project> getProjectsByStatus(){
		return ManagerHelper.getProjectManager().getProjectsByStatus();
	}
	@GET
	@Path("getNearEndProject")
	public List<Project> getendproject(){
		return ManagerHelper.getProjectManager().getNearEndProject();
	}
	@GET
	@Path("delete")
	public Reply deleteproject(@QueryParam("id")int id){
		return  ManagerHelper.getProjectManager().deleteProject(id);
	}
	@GET
	@Path("create")
	public Project create(@QueryParam("projectname") 
	String projectname,@QueryParam("customer") 
	int customer,@QueryParam("customerprojectmanager")
	String customerprojectmanager,@QueryParam("projectmanageremail")
	String projectmanageremail,@QueryParam("projectmanagerephone") 
	String projectmanagerephone, @QueryParam("startdate") String startdate,
	 @QueryParam("enddate") String enddate) {
		return (Project)ManagerHelper.getProjectManager().create(projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerephone,startdate, enddate);
	}
	@GET
	@Path("update")
	public String update(@QueryParam("id") int id,@QueryParam("projectname") 
	String projectname,@QueryParam("customer") int customer,
			@QueryParam("customerprojectmanager")
	String customerprojectmanager,@QueryParam("projectmanageremail")
	String projectmanageremail,
			@QueryParam("projectmanagerephone") String projectmanagerephone,
			@QueryParam("startdate") String startdate,@QueryParam("startdate") String enddate) {
		return (String)ManagerHelper.getProjectManager().update(id , projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerephone, startdate,enddate);
	}
	
}
