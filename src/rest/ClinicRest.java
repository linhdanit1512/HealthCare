package rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.ClinicDAO;

@Path("/clinic")
public class ClinicRest {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getClinic(@PathParam("id") String id) {
		int i = Integer.parseInt(id);
		return ClinicDAO.getClinic(i).toJson();
	}
	
	@Path("/update/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean update(@PathParam("id") String id){
		return false;
	}

}
