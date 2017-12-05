package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.omg.CORBA.PUBLIC_MEMBER;

import entity.User;
import manager.MailHelper;



@Path("/users")
public class Userservice {
	
	
	

	@GET
	@Path("userid")
	public User getuser(@QueryParam("id") Integer id) {
		return manager.ManagerHelper.getUserManager().get(id);
	}

	@GET
	@Path("username")

	public User getuserbyName(@QueryParam("username") String name,@QueryParam("password")String password) {
		return manager.ManagerHelper.getUserManager().getByName(name,password);
	}
	
	@GET
	@Path("allusers")

	public List<User> getallusers() {

		return manager.ManagerHelper.getUserManager().getallusers();
	}
	
	@GET
	@Path("sendmail")
	
	public User forgetPassword(@QueryParam("user")String username) {
		
		return manager.ManagerHelper.getUserManager().forgetPassword(username);
	}
	
	
	

}
