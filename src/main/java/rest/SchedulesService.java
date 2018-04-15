package rest;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.DoctorDAO;
import dao.ScheduleDAO;
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

	/**
	 * 
	 * @param idDoctor:
	 *            mã số Doctor
	 * @param scheduleList:
	 *            jsonString của 1 List<Schedule>
	 * @return
	 */
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
					else {
						Schedules sche = new Schedules(s);
						ScheduleDAO.insert(sche);
						set.add(sche);
					}
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

	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String add(@FormParam("dates") String dates, @FormParam("startTime") int startTime,
			@FormParam("stopTime") int stopTime, @FormParam("workspace") String workspace,
			@Context HttpServletResponse response) {
		Schedules sche = ScheduleDAO.getSchedules(dates, startTime, stopTime, workspace);
		if (sche == null) {
			sche = new Schedules(dates, startTime, stopTime, workspace);
			ScheduleDAO.insert(sche);
			return "Thêm Lịch làm việc thành công";
		} else {
			return "Lịch làm việc này đã tồn tại";
		}
	}

	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String update(@PathParam("id") int idSchedule, @FormParam("dates") String dates,
			@FormParam("startTime") int startTime, @FormParam("stopTime") int stopTime,
			@FormParam("workspace") String workspace, @Context HttpServletResponse response) {
		Schedules sche = ScheduleDAO.getSchedules(idSchedule);
		if (sche == null) {
			sche = new Schedules(dates, startTime, stopTime, workspace);
			ScheduleDAO.insert(sche);
			return "Lịch làm việc này chưa có, đã thêm";
		} else {
			sche.setDates(dates);
			sche.setStartTime(startTime);
			sche.setStopTime(stopTime);
			sche.setWorkspace(workspace);
			return ScheduleDAO.update(sche) ? "Cập nhât lịch làm việc thành công" : "Cập nhật lịch thất bại";
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String delete(@PathParam("id") int idSchedule) {
		Schedules sche = ScheduleDAO.getSchedules(idSchedule);
		if (sche != null) {
			return ScheduleDAO.delete(sche) ? "Xóa lịch thành công" : "Xóa lịch thất bại";
		} else {
			return "Lịch này chưa có";
		}
	}
}
