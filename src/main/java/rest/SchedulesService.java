package rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import DAO.DoctorDAO;
import DAO.ScheduleDAO;
import entity.Doctor;
import entity.Schedules;

@Path("/schedules")
public class SchedulesService {
	@POST
	@Path("/registry")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String registry(@FormParam("idDoctor") int idDoctor, @FormParam("dates") String dates,
			@FormParam("startTime") int startTime, @FormParam("stopTime") int stopTime,
			@FormParam("workspace") String workspace, @Context HttpServletResponse response) {
		Doctor doctor = DoctorDAO.getDoctor(idDoctor);
		if (doctor != null) {
			Schedules sche = ScheduleDAO.getSchedules(dates, startTime, stopTime, workspace);
			if (sche == null) {
				sche = new Schedules(dates, startTime, stopTime, workspace);
				ScheduleDAO.insert(sche);
			}
			Set<Schedules> set = new HashSet<Schedules>();
			set.add(sche);
			doctor.setScheduleses(set);
			if (DoctorDAO.update(doctor))
				return "Đăng ký lịch trực thành công";
			else
				return "Đăng ký lịch trực không thành công";
		} else
			return "Không tìm thấy bác sĩ này";
	}

	@POST
	@Path("/registry/list")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String registryList(@FormParam("idDoctor") int idDoctor, @FormParam("scheduleList") String scheduleList) {
		Doctor doctor = DoctorDAO.getDoctor(idDoctor);
		if (doctor != null) {
			Set<Schedules> setSchedule = Schedules.parseJsonListToSet(scheduleList);
			Set<Schedules> set = new HashSet<Schedules>();
			if (setSchedule != null) {
				for (Schedules s : setSchedule) {
					if (ScheduleDAO.getSchedules(s.getIdSchedule()) != null)
						set.add(s);
					else
						set.add(new Schedules(s));

				}
			}
			doctor.setScheduleses(set);
			if (DoctorDAO.update(doctor))
				return "Đăng ký lịch trực thành công";
			else
				return "Đăng ký lịch trực không thành công";
		} else
			return "Không tìm thấy bác sĩ";
	}
}
