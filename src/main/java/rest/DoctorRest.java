package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/doctor")
public class DoctorRest {

	@Path("{username}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		return "";
	}
}
