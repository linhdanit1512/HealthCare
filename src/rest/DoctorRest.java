package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/doctor")
public class DoctorRest {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String login(String username, String password){
		return "";
	}
}
