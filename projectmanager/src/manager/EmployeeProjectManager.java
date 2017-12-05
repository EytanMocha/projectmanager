package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.HourReport;
import entity.Project;

public class EmployeeProjectManager {

	private final EntityManager entityManager;

	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void create(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void delete(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}

	public EmployeeProject get(int id) {
		return entityManager.find(EmployeeProject.class, id);
	}

	public Reply createEmployeeProject(int employeeid, int projectid) {

		Employee emp = ManagerHelper.getEmploeeManager().get(employeeid);

		Project pro = ManagerHelper.getProjectManager().get(projectid);

		EmployeeProject employeeProject = new EmployeeProject(emp, pro);

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Reply updateEmployeeProject(int id, int employeeid, int projectid) {

		Employee emp = ManagerHelper.getEmploeeManager().get(id);
		Project pro = ManagerHelper.getProjectManager().get(id);
		EmployeeProject employeeProject = new EmployeeProject(id, emp, pro);

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(employeeProject);
			entityManager.getTransaction().commit();
			Reply r = new Reply();
			return r;
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public Reply deleteEmployeeProject(int id) {
		System.out.println("----> manager EmployeeProject");
		EmployeeProject employeeProject = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public List<EmployeeProject> EmployeeProject() {

		String s = "SELECT * FROM projectmanager.employeeproject;";
		return (List<entity.EmployeeProject>) entityManager.createNativeQuery(s, EmployeeProject.class).getResultList();
	}
                                      /*for employee projects*/
	public List<EmployeeProject> getListOfProjects(int id) {
		String sql = "select * from projectmanager.employeeproject ep "
				+ "inner join projectmanager.employee e on e.id = ep.employee "
				+ "inner join projectmanager.project p on p.id = ep.project "
				+ "inner join projectmanager.user u on u.id = e.user  where  u.id ="+id;
		
		return(List<EmployeeProject>) entityManager.createNativeQuery(sql, EmployeeProject.class).getResultList();
	}

	public List<EmployeeProject> getListOfProjectsAndEmployees(int id) {
		String sql = "SELECT * FROM projectmanager.employeeproject emp "
				+ " inner join projectmanager.project p on p.id = emp.project "
				+ " inner join projectmanager.employee e on e.id = emp.employee " + " where p.id =" + id;
		return entityManager.createNativeQuery(sql, EmployeeProject.class).getResultList();
	}
}