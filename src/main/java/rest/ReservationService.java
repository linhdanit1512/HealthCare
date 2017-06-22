package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entity.Reservation;
import util.ReservationUtil;

@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ReservationService {
	@GET
	public List<List<Reservation>> getReservations() {
		return new ArrayList<List<Reservation>>(ReservationUtil.listAllReservation.values());
	}
	
	@GET
	@Path("/{user}")
	public List<Reservation> getReservationOfDoctor(@PathParam("user") String username) {
		return ReservationUtil.listAllReservation.get(username);
	}
}
