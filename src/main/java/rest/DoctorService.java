package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.DoctorDAO;
import entity.Doctor;
import util.DoctorUtil;

@Path("/doctor")
public class DoctorService {

	@GET
	@Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
	public List<Doctor> getDoctors() {
		new DoctorUtil();
		return new ArrayList<Doctor>(DoctorUtil.listAllDoctors.values());
	}

	@GET
	@Path("/login/{user}/{pass}")
	@Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
	public Doctor login(@PathParam("user") String username, @PathParam("pass") String password) {
		return DoctorDAO.login(username, password);
	}

	@POST
	@Path("/register/{doctor}")
	@Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean register(@PathParam("doctor") Doctor doctor) {
		if(doctor==null)
			return false;
		try {
			if (DoctorDAO.register(doctor)) {
				if (DoctorDAO.insert(doctor))
					return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
