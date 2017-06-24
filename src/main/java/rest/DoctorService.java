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
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	public boolean register(@FormParam("userName") String userName, @FormParam("password") String password,
			@FormParam("name") String name, @FormParam("specilaty") String special, @FormParam("degree") String degree,
			@FormParam("experience") String experient, @FormParam("email") String email,
			@FormParam("doctorAddress") String doctorAddress, @FormParam("phone") String phone,
			@FormParam("passport") String passport, @Context HttpServletResponse servletResponse) {
		try {
			int experience = Integer.parseInt(experient);
			Specialty specialty = SpecialtyDAO.getSpecialtyByName(special);
			if (specialty == null) {
				specialty = new Specialty(special);
			}
			Date timeCreate = new Date();
			Doctor doctor = new Doctor(specialty, userName, name, password, email, phone, passport, degree, experience,
					doctorAddress, timeCreate, false);
			if (DoctorDAO.register(doctor)) {
				DoctorDAO.insert(doctor);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@GET
	@Path("/forgetpassword/{email}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String forgetpass(@PathParam("email") String email) {
		try {
			Doctor doctor = DoctorDAO.getDoctorEmail(email);
			if (doctor != null) {
				Random r = new Random();
				long i = r.nextLong() * 5684452;
				String num = (i + "").substring(0, 6);
				doctor.setPasswords(num);

				if (DoctorDAO.update(doctor)) {
					// can them phan gui email o day nua
					return num;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/check/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean checkedDoctor(@PathParam("id") int id) {
		Doctor doctor = DoctorDAO.getDoctor(id);
		if (doctor != null) {
			doctor.setIsCheck(true);
			return true;
		}
		return false;
	}

	@PUT
	@Path("/update/clinic/{idDoctor}")
	public boolean updateClinic(@PathParam("idDoctor") int id, @FormParam("clinicName") String clinicName,
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
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@PUT
	@Path("/update/info/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	public boolean update(@PathParam("id") int id, @FormParam("password") String password,
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
							return false;
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
							return false;
					}
					doctor.setPassport(passport);
				}
				return DoctorDAO.update(doctor);
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public boolean delete(@PathParam("id") int id) {
		try {
			Doctor doctor = DoctorDAO.getDoctor(id);
			if (doctor != null)
				if (DoctorDAO.delete(doctor))
					return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
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
