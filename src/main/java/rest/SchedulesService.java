package rest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/schedules")
public class SchedulesService {
	@GET
	@Path("/reegistry")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String registry(@FormParam("dates") String dates, @FormParam("startTime") int startTime,
			@FormParam("stopTime") int stopTime, @Context HttpServletResponse response) {
		
		return "";
	}
}
