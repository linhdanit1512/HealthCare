package rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import DAO.ClinicDAO;
import DAO.DoctorDAO;
import DAO.SpecialtyDAO;
import entity.Clinic;
import entity.Doctor;
import entity.Message;
import entity.Schedules;
import entity.Specialty;
import util.DoctorUtil;
import util.MailUtil;
import util.SendMail;

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
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String login(@PathParam("user") String username, @PathParam("pass") String password) {
		return DoctorDAO.login(username, password).toJson();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String register(@FormParam("userName") String userName, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("specialty") String special, @FormParam("degree") String degree,
			@FormParam("experience") String experient, @FormParam("email") String email,
			@FormParam("doctorAddress") String doctorAddress, @FormParam("phone") String phone,
			@FormParam("passport") String passport, @Context HttpServletResponse servletResponse) {
		try {
			int experience = Integer.parseInt(experient);
			Specialty specialty = SpecialtyDAO.getSpecialtyByName(special);
			if (specialty == null) {
				specialty = new Specialty(special);
				SpecialtyDAO.insert(specialty);
			}
			Date timeCreate = new Date();
			Doctor doctor = new Doctor(specialty, userName, name, password, email, phone, passport, degree, experience,
					doctorAddress, timeCreate, false);
			if (DoctorDAO.register(doctor)) {
				return DoctorDAO.insert(doctor)?"Đăng ký thành công":"Đăng ký thất bại";
			}
			return "Không đăng ký được";
		} catch (Exception e) {
			return "Lỗi đăng ký";
		}
	}

	@PUT
	@Path("/forgetpassword")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String forgetpass(@FormParam("email") String email, @Context HttpServletResponse servletResponse) {
		try {
			Doctor doctor = DoctorDAO.getDoctorEmail(email);
			if (doctor != null) {
				Random r = new Random();
				long i = (r.nextLong() + 1) * 5684452;
				String pass = (i + "").substring(0, 6);
				doctor.setPasswords(pass);
				if (DoctorDAO.update(doctor)) {
					SendMail.sendMail(email, MailUtil.forgetPasswordTemplete(pass, doctor.getUsername()));
					return pass;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/check/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String checkedDoctor(@PathParam("id") int id) {
		Doctor doctor = DoctorDAO.getDoctor(id);
		if (doctor != null) {
			doctor.setIsCheck(true);
			return "Duyệt thành công";
		}
		return "Duyệt thất bại";
	}

	@PUT
	@Path("/update/clinic/{idDoctor}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	public String updateClinic(@PathParam("idDoctor") int id, @FormParam("clinicName") String clinicName,
			@FormParam("clinicAddress") String clinicAddress, @Context HttpServletResponse servletResponse) {
		try {
			Doctor doctor = DoctorDAO.getDoctor(id);
			if (doctor != null) {
				Clinic clinic = ClinicDAO.getClinicNameAndAddress(clinicName, clinicAddress);
				if (clinic == null) {
					clinic = new Clinic(clinicAddress, clinicAddress);
				}
				doctor.setClinic(clinic);
				if (DoctorDAO.update(doctor))
					return "Cập nhật phòng khám thành công";
			}
			return "Cập nhật phòng khám thất bại";
		} catch (Exception e) {
			return "Đã xảy ra lỗi khi cập nhật phòng khám";
		}
	}

	@PUT
	@Path("/update/info/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String update(@PathParam("id") int id, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("specialty") String special, @FormParam("degree") String degree,
			@FormParam("experience") String experient, @FormParam("email") String email,
			@FormParam("doctorAddress") String doctorAddress, @FormParam("phone") String phone,
			@FormParam("passport") String passport, @Context HttpServletResponse servletResponse) {
		try {
			Doctor doctor = DoctorDAO.getDoctor(id);
			if (doctor != null) {
				if (check(password))
					doctor.setPasswords(password);
				if (check(name))
					doctor.setNameDoctor(name);
				if (check(special)) {
					Specialty specialty = SpecialtyDAO.getSpecialtyByName(special);
					if (specialty == null) {
						specialty = new Specialty(special);
					}
				}
				if (check(degree))
					doctor.setDegree(degree);
				if (check(experient)) {
					int experience = Integer.parseInt(experient);
					doctor.setExperience(experience);
				}
				if (check(email)) {
					Doctor d = DoctorDAO.getDoctorEmail(email);
					if (d != null) {
						if (d.getIdDoctor() != doctor.getIdDoctor())
							return "Email đã được sử dụng";
					}
					doctor.setEmail(email);
				}
				if (check(doctorAddress))
					doctor.setAddress(doctorAddress);
				if (check(phone))
					doctor.setPhone(phone);
				if (check(passport)) {
					Doctor d = DoctorDAO.getDoctorPassport(passport);
					if (d != null) {
						if (d.getIdDoctor() != doctor.getIdDoctor())
							return "Số CMND/Hộ chiếu đã có người sủ dụng";
					}
					doctor.setPassport(passport);
				}
				return DoctorDAO.update(doctor)? "Cập nhật thành công": "Cập nhật thất bại";
			}
			return "Cập nhật thất bại";
		} catch (Exception e) {
			return "Lỗi cập nhật";
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String delete(@PathParam("id") int id) {
		try {
			Doctor doctor = DoctorDAO.getDoctor(id);
			if (doctor != null)
				if (DoctorDAO.delete(doctor))
					return "Xóa bác sĩ thành công";
			return "Xóa bác sĩ thất bại";
		} catch (Exception e) {
			e.printStackTrace();
			return "Lỗi xóa bác sĩ";
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getAllDoctor() {
		try {
			List<Doctor> list = DoctorDAO.getAllDoctor();
			if (list != null) {
				return Doctor.toJsonList(list);
			} else
				return "{\"doctorList\": null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"doctorList\": null}";
		}
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getDoctor(@PathParam("id") int id) {
		try {
			Doctor doctor = DoctorDAO.getDoctor(id);
			return doctor.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"doctor\":null}";
		}
	}

	@GET
	@Path("/schedule/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getSchedule(@PathParam("id") int id) {
		try {
			List<Schedules> set = DoctorDAO.getSchedule(id);
			if (set != null)
				return Schedules.toJsonList(set);
			return "{\"scheduleList\":null}";
		} catch (Exception e) {
			return "{\"scheduleList\": null}";
		}
	}

	@GET
	@Path("/message/{idDoctor}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getMessages(@PathParam("idDoctor") int id) {
		try {
			List<Message> list = DoctorDAO.getMessages(id);
			if (list != null) {
				return Message.toJsonList(list);
			}
			return "{\"messageList\":null}";
		} catch (Exception e) {
			return "{\"message\":null}";
		}
	}

	private boolean check(String s) {
		if (s == null)
			return false;
		if ("".equals(s))
			return false;
		return true;
	}
}
