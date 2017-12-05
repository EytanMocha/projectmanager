package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.HourReport;
import entity.Project;

public class HourReportManager {

	private final EntityManager entityManager;

	public HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();
	}

	public void create(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourReport);
		entityManager.getTransaction().commit();
	}

	public HourReport createHourReport(int userid, int project, String date, String starthour, String endhour,
			String description) {
      
		String s= "SELECT * FROM projectmanager.employee e "
        		+"inner join user u on e.user=u.id "
        		 +"where u.id = "+userid;

		Employee emp = (Employee)entityManager.createNativeQuery(s, Employee.class).getSingleResult();
		Project pro = ManagerHelper.getProjectManager().get(project);
	
		
		HourReport hourreport = new HourReport(emp, pro,date, starthour, endhour, description);

	

		entityManager.getTransaction().begin();
		entityManager.persist(hourreport);
		entityManager.getTransaction();
		((EntityTransaction) entityManager).commit();

		return hourreport;

	}

	public String updateHourReport(int id, int employee, int project,String date, String starthour, String endhour,String description) {
		Employee em = ManagerHelper.getEmploeeManager().get(employee);
		Project pr = ManagerHelper.getProjectManager().get(project);
		Reply reply = new Reply();
		try {
			entityManager.getTransaction().begin();
			System.out.println("update");
			HourReport hourreport = new HourReport(em, pr, date, starthour, endhour, description);
			entityManager.merge(hourreport);
			entityManager.getTransaction().commit();
			return reply.getMsg();
		} catch (Exception e) {
			reply.setMsg("try again");
			return reply.getMsg();
		}

	}

	public Reply deleteHourReport(int id) {
		HourReport hourreport = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(hourreport);
			entityManager.getTransaction().commit();

			return new Reply();
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage() + id);

		}
		return Reply.DEFAULT_REPLY;

	}

	public void delete(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourReport);
		entityManager.getTransaction().commit();
	}

	public HourReport get(Integer id) {
		return entityManager.find(HourReport.class, id);
	}

	public List<HourReport> getAllHourFromReports() {

		String s = "SELECT * FROM projectmanager.hourreport";
		return (List<HourReport>) entityManager.createNativeQuery(s, HourReport.class).getResultList();
	}
	public List<HourReport> getEmployeeByUserReport(int userid ) {
		
		String s = "SELECT * FROM projectmanager.hourreport "
				
				+ "inner join employee e on e.id = hourreport.employee "
				+ "inner join user u on u.id =e.user "
				+ "where u.id = "+userid + " order by date";
				
		return (List<HourReport>) entityManager.createNativeQuery(s, HourReport.class).getResultList();
	}

	public List<HourReport> getEmployeeReport(Integer employee) {

		Employee emp = ManagerHelper.getEmploeeManager().get(employee);

		String s = "SELECT * FROM projectmanager.hourreport where employee like ";
		return (List) entityManager.createNativeQuery(s + emp, HourReport.class).getResultList();
	}

	public List<HourReport> getHourReports() {
		String sql = "SELECT h.date, h.starthour, h.endhour, e.firstname, e.lastname, p.projectname, c.companyname FROM hourreport h "
				+ "inner join projectmanager.employee e on h.employee = e.id "
				+ "inner join projectmanager.project p on h.project = p.id "
				+ "inner join projectmanager.customer c on p.customer = c.id " + "order by date";
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}

	public List<HourReport> getHoursReports() {
		String sql = "SELECT  h.date, h.starthour, h.endhour, e.firstname, e.lastname, p.projectname, c.companyname FROM hourreport h "
				+ "inner join projectmanager.employee e on h.employee = e.id "
				+ "inner join projectmanager.project p on h.project = p.id "
				+ "inner join projectmanager.customer c on p.customer = c.id";
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
	/*reports for the last week for employee*/
	public List<HourReport> getLastSevenDays(int userid) {
		String sql = "SELECT h.id ,h.employee ,h.project, h.date, h.starthour, h.endhour,h.description from hourreport h "
				+ "inner  join employee e on h.employee=e.id  " + "inner join user u on e.user =u.id  "
				+ "where h.date > date_sub(curdate(), interval 1 week) and u.id =" + userid;
		System.out.println("getLastSevenDays" + sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}
/*reports for the last month for employee*/
	public List<HourReport> getLastThirtyDays(int userid) {
		String sql = "SELECT h.id ,h.employee ,h.project, h.date, h.starthour, h.endhour,h.description from hourreport h "
				+"inner  join employee e on h.employee=e.id  inner join user u on e.user =" 	
				+ "where h.date > date_sub(curdate(), interval 4 week) and u.id =" + userid;
		System.out.println("getLastThirtyDays" + sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
	/*report by year and month*/
	public List<HourReport> getByYearMonthManager(String year, String month, int employeeid,
			int projectid, int customerid) {
			
	
			
		String sql = "select * from projectmanager.hourreport h "
				+ "inner join projectmanager.employee e on h.employee = e.id "
				+ "inner join projectmanager.project p on h.project = p.id "
				+ "where year(h.date) like'"+year+"' and month(h.date) like '"+month+"' ";
										
					if(employeeid != 0){
						sql += " and h.employee = " + employeeid   ;
						System.out.println("employee");
					}
					
					if( projectid != 0){
						sql +=" and  h.project =  " + projectid ;
						System.out.println("project");
					}
					
					if(customerid != 0){
						sql += " and p.customer =  " + customerid ;
						System.out.println("customer");
					}
			

		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
	public List<HourReport> getAllHourReports(int userId) {
		
		String sql = "SELECT * FROM projectmanager.hourreport  "
				+ "inner join projectmanager.employee e on hourreport.employee = e.id "
				+ "inner join projectmanager.user u on e.user= u.id "
				+ " where date >= curdate() - INTERVAL 6 DAY "
				+ "and date <= curdate() "
				+ "and u.id ="+userId+" order by date";  
		System.out.println("getAllHourReports");
		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
		
	}
	/*customer hours*/
	public List<HourReport> getCustomerHourReport(int userid){
		String sql = "SELECT  h.id , project.projectname , customer.companyname , h.date , h.starthour , h.endhour , h.description FROM projectmanager.hourreport h "
				     + "inner join project on project.id = h.project inner join customer on customer.id = project.customer "
				     + "inner join user on user.id = customer.user and user.id = "+userid;
		return (List<HourReport>) entityManager.createNativeQuery(sql,HourReport.class).getResultList();
	
	
	}
}
