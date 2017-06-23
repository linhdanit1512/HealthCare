package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.ClinicDAO;
import entity.Clinic;

@Path("/clinic")
public class ClinicService {

	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getClinic(@PathParam("id") String id) {
		try {
			int i = Integer.parseInt(id);
			Clinic clinic = ClinicDAO.getClinic(i);
			if (clinic != null)
				return clinic.toJson();
			else
				return "{\"clinic\":null";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinic\":null";
		}
	}

	@GET
	@Path("/search/name/{name}")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchByName(@PathParam("name") String name) {
		try {
			if (name != null && !"".equals(name)) {
				List<Clinic> clinic = ClinicDAO.getClinicByName(name);
				if (clinic != null)
					return Clinic.toJsonList(clinic);
				else {
					return "{\"clinicList\":null";
				}
			} else
				return "{\"clinicList\":null";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinicList\":null";
		}
	}

	@GET
	@Path("/search/address/{address}")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchByAddress(@PathParam("address") String address) {
		try {
			if (address != null && !"".equals(address)) {
				List<Clinic> list = ClinicDAO.getClinicByAddress(address);
				if (list != null)
					return Clinic.toJsonList(list);
				else
					return "{\"clinicList\":null";
			} else
				return "{\"clinicList\":null";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinicList\":null";
		}
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public boolean delete(@PathParam("id") String id) {
		try {
			int i = Integer.parseInt(id);
			Clinic clinic = ClinicDAO.getClinic(i);
			return ClinicDAO.delete(clinic);
		} catch (Exception e) {
			return false;
		}
	}
}
