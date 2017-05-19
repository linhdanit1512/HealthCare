package restful.healthCare.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import restful.healthCare.entity.Doctor;
import restful.healthCare.util.DoctorUtil;

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
		return DoctorUtil.loginDoctor(username, password);
	}
}
