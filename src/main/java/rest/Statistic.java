package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ClinicDAO;
import dao.DoctorDAO;
import entity.Reservation;

@Path("/statistic")
public class Statistic {
	@Path("/nondoctor")
	@GET
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getClinicNonDoctor() {
		try {
			int x = ClinicDAO.getClinicNonDoctor();
			return x + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	@Path("/hasdoctor")
	@GET
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getClinicHasDoctor() {
		try {
			int x = ClinicDAO.getClinicHasDoctor();
			return x + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	@Path("/reservationunchecked/{idDoctor}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getReservationUnchecked(@PathParam("idDoctor") int idDoctor) {
		try {
			List<Reservation> list = DoctorDAO.getReservationUnchecked(idDoctor);
			if (list != null) {
				return Reservation.toJsonList(list);
			}
			return "{\"reservationList\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"reservationList\":null}";
		}
	}

	@Path("/reservationchecked/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getReservationChecked(@PathParam("id") String idDoctor) {
		try {
			if (idDoctor != null && idDoctor.length() > 0) {
				int id = Integer.parseInt(idDoctor);
				List<Reservation> list = DoctorDAO.getReservationChecked(id);
				if (list != null) {
					return Reservation.toJsonList(list);
				}
			}
			return"{\"reservationList\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"reservationList\":null}";
		}
	}
}
