package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ClinicDAO;
import entity.Clinic;

@Path("/clinic")
public class ClinicService {

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
				return "{\"" + Clinic.class.getName() + "\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"" + Clinic.class.getName() + "\":null}";
		}
	}

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String all() {
		try {
			List<Clinic> clinic = ClinicDAO.getAllClinic();
			if (clinic != null)
				return Clinic.toJsonList(clinic);
			else
				return "{\"clinicList\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinicList\":null}";
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
					return "{\"clinicList\":null}";
				}
			} else
				return "{\"clinicList\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinicList\":null}";
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
					return "{\"clinicList\":null}";
			} else
				return "{\"clinicList\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"clinicList\":null}";
		}
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String delete(@PathParam("id") int id) {
		try {
			Clinic clinic = ClinicDAO.getClinic(id);
			return ClinicDAO.delete(clinic) ? "Xóa phòng khám thành công" : "Xóa phòng khám thất bại";
		} catch (Exception e) {
			return "Không thể xóa";
		}
	}
}
