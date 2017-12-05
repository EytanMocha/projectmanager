package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import entity.EmployeeProject;
import manager.ManagerHelper;
import manager.Reply;

@Path("/employeeProject")
public class EmployeeProjectServices {
	

	@GET
	@Path("deleteEmployeeProject")
	public Reply deleteEmployeeProject(@QueryParam("id") int id) {
		System.out.println("delete service EmployeeProject");
		return ManagerHelper.getEmployeeProjectManager().deleteEmployeeProject(id);
	}
	
	@GET
	@Path("createEmployeeProject")
	public Reply createEmployeeProject(@QueryParam("employee") int employee, @QueryParam("project") int project) {
		System.out.println("create service EmployeeProject");
		return  ManagerHelper.getEmployeeProjectManager().createEmployeeProject(employee, project);
	}
	
	@GET
	@Path("updateEmployeeProject")
	public Reply updateEmployeeProject(@QueryParam("id") int id, @QueryParam("employeeid") int employeeid, @QueryParam("projectid") int projectid) {
		return  ManagerHelper.getEmployeeProjectManager().updateEmployeeProject(id, employeeid, projectid);
	}
	@GET
	@Path("getallemployeesConnect")
	public List<EmployeeProject> employeeProjects(){
		return ManagerHelper.getEmployeeProjectManager().EmployeeProject();
		
	}
	@GET
	@Path("getListOfProjects")
	public List<EmployeeProject> getListOfProjectsAndEmployees(@QueryParam("id") int id){
		return ManagerHelper.getEmployeeProjectManager().getListOfProjects(id);
		
	}
	@GET
	@Path("getListOfProjectsAndEmployees")
	public List<EmployeeProject> getListOfProjects(@QueryParam("id") int id){
		return ManagerHelper.getEmployeeProjectManager().getListOfProjectsAndEmployees(id);
		
	}

}
