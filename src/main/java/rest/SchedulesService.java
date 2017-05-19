package restful.healthCare.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import restful.healthCare.entity.Schedules;
import restful.healthCare.util.SchedulesUtil;

@Path("/schedules")
public class SchedulesService {
	@GET
	@Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
	public List<List<Schedules>> getReservations() {
		return new ArrayList<List<Schedules>>(SchedulesUtil.listAllSchedules.values());
	}
	
	@GET
	@Path("/{user}")
	@Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
	public List<Schedules> getReservationOfDoctor(@PathParam("user") String username) {
		return SchedulesUtil.listAllSchedules.get(username);
	}
}
