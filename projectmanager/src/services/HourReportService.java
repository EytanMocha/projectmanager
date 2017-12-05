package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.HourReport;
import manager.ManagerHelper;


@Path("/hourReport")
public class HourReportService {

	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectmanager");

	public static EntityManager entityManager = entityManagerFactory.createEntityManager();

	@GET
	@Path("get")
	public HourReport getReportById(@QueryParam("id") int id) {
		return ManagerHelper.getHourreportManager().get(id);
	}

	@GET
	@Path("updateHourReport")
	public String updateHourReport(@QueryParam("id") int id, @QueryParam("employee") int employee,
			@QueryParam("project") int project, @QueryParam("date") String date, @QueryParam("starthour") String starthour,
			@QueryParam("endhour") String endhour, @QueryParam("description") String description) {
		return ManagerHelper.getHourreportManager().updateHourReport(id, employee, project,date, starthour, endhour,
				description);
	}

	@GET
	@Path("createHourReport")

	public HourReport createHourReport(@QueryParam("employee") int employee, @QueryParam("project") int project,
			@QueryParam("date") String date, @QueryParam("starthour") String starthour, @QueryParam("endhour") String endhour,
			@QueryParam("description") String description) {
		return (HourReport) ManagerHelper.getHourreportManager().createHourReport(employee, project, date, starthour,endhour,
				description);
	}

	@GET
	@Path("getAllAllHours")
	public List<HourReport> getAllHourFromReports () {
		return ManagerHelper.getHourreportManager().getAllHourFromReports();
	}

	@GET
	@Path("getEmployeeReport")
	public List<HourReport> getEmployeeReport(@QueryParam("employee")int employee) {
		return ManagerHelper.getHourreportManager().getEmployeeReport(employee);
	}

	@GET
	@Path("getByYearMonthManager")
	public List<HourReport> gethourreport(@QueryParam("year") String year ,@QueryParam("month") String month,
			 @QueryParam("projectid") int projectid,@QueryParam("employeeid") int employee,
			@QueryParam("customerid") int customer) {
		

			return ManagerHelper.getHourreportManager().getByYearMonthManager(year, month, employee, projectid, customer);

		}


	

	@GET
	@Path("EmployeeReportForCustomer")
	public List<HourReport> getCustomerHourReport(@QueryParam("userid") int userid) {

		return ManagerHelper.getHourreportManager().getCustomerHourReport(userid);
	}
	@GET
	@Path("getLastSevenDays")
	public List<HourReport> getLastSevenDaysget(@QueryParam("userid") int userid) {
		
		return ManagerHelper.getHourreportManager().getLastSevenDays(userid);
	}
	@GET
	@Path("getAllRport")
	public List<HourReport> getEmployeeByUserReport(@QueryParam("userid") int userid) {

		return ManagerHelper.getHourreportManager().getEmployeeByUserReport(userid);
		
	
}
	

}