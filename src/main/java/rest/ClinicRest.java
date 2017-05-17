package rest;

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
public class ClinicRest {

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getClinic(@PathParam("id") String id) {
		try {
			int i = Integer.parseInt(id);
			return ClinicDAO.getClinic(i).toJson();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

//	@Path("/all")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
//	public Cookie test() {
//		List<Clinic> list = ClinicDAO.getAllClinic();
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (int i = 0; i < list.size(); i++) {
//			sb.append(list.get(i).toJson());
//			if (i < list.size() - 1)
//				sb.append(",");
//		}
//		sb.append("]");
//		Cookie cookie = new Cookie("listClinic", sb.toString());
//		return cookie;
//	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
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
