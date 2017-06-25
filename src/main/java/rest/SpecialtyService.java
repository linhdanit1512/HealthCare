package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.SpecialtyDAO;
import entity.Specialty;

@Path("/specialty")
public class SpecialtyService {

	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getSpecialty(@PathParam("id") int id) {
		try {
			Specialty sp = SpecialtyDAO.getSpecialty(id);
			if (sp != null)
				return sp.toJson();
			else
				return "{\"" + Specialty.class.getName() + "\":null}";
		} catch (Exception e) {
			return "{\"" + Specialty.class.getName() + "\":null}";
		}
	}

	@GET
	@Path("/search/{name}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchByName(@PathParam("name") String name) {
		try {
			if (name != null && !"".equals(name)) {
				Specialty s = SpecialtyDAO.getSpecialtyByName(name);
				if (s != null)
					return s.toJson();
			}
			return "{\"" + Specialty.class.getName() + "\":null}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"" + Specialty.class.getName() + "\":null}";
		}
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	public String insert(@FormParam("specialtyName") String specialtyName) {
		if (specialtyName != null && !"".equals(specialtyName)) {
			Specialty check = SpecialtyDAO.getSpecialtyByName(specialtyName);
			if (check == null) {
				Specialty special = new Specialty(specialtyName);
				return SpecialtyDAO.insert(special) ? "Thêm Chuyên khoa thành công" : "Thêm chuyên khoa thất bại";
			}
		}
		return "Không thể thêm Chuyên khoa";
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String update(@FormParam("idSpecialty") int idSpecialty, @FormParam("specialtyName") String specialtyName) {
		Specialty s = SpecialtyDAO.getSpecialty(idSpecialty);
		if (s != null) {
			s.setNameSpecialty(specialtyName);
			return SpecialtyDAO.update(s) ? "Cập nhật chuyên khoa thành công" : "Cập nhật chuyên khoa thất bại";
		} else if (s == null && specialtyName != null && !"".equals(specialtyName)) {
			s = new Specialty(specialtyName);
			return SpecialtyDAO.insert(s) ? "Thêm chuyên khoa thành công" : "Thêm chuyên khoa thất bại";
		}
		return "Không thể cập nhật chuyên khoa";
	}

	@DELETE
	@Path("/delete/{id}")
	public String delete(@PathParam("id") int id) {
		Specialty special = SpecialtyDAO.getSpecialty(id);
		return SpecialtyDAO.delete(special) ? "Xóa chuyên khoa thành công" : "Xóa chuyên khoa thất bại";
	}

}
