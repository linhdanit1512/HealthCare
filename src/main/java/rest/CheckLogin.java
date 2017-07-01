package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.ClinicDAO;
import entity.Clinic;

@Path("/checkLogin")
public class CheckLogin {
	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getClinic(@PathParam("id") int id) {
		try {
			Clinic clinic = ClinicDAO.getClinic(id);
			if (clinic != null)
				return clinic.toJson();
			else
				return "{\""+Clinic.class.getName()+"\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\""+Clinic.class.getName()+"\":null}";
		}
	}
}
