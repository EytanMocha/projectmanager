package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.Reply;

@Path("/Properties")
public class Properties {

	@GET
	@Path("getHourList")
	public String getHourList() {
		String hours = PropsHelper.get("hours");
		return hours;
	}
	@GET
	@Path("setHourList")
	public String setHourList(@QueryParam("startHour") String startHour, @QueryParam("endHour") String endHour) {
		String sumOfHours = startHour+","+endHour;
		PropsHelper.set("hours", sumOfHours);
		return sumOfHours;
	}
	
	@GET
	@Path("getDays")
	public String getdays() {
		String days = PropsHelper.get("days");
		return days;
	}
	

	@GET
	@Path("setdaysofWork")
	public String setDays(@QueryParam("sunday") String sunday, @QueryParam("monday") String monday,
			@QueryParam("tuesday") String tuesday, @QueryParam("wednesday") String wednesday,
			@QueryParam("thursday") String thursday, @QueryParam("friday") String friday,
			@QueryParam("saturday") String saturday) {
		return PropsHelper.set("days", " " + sunday + "," + " " + monday + "," + " " + tuesday + "," + " " + wednesday
				+ "," + " " + thursday + "," + " " + friday + "," + " " + saturday + ",");
	}

}
