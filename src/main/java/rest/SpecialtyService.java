package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	public String getSpecialty(@PathParam("id") String id) {
		try {
			int i = Integer.parseInt(id);
			return SpecialtyDAO.getSpecialty(i).toJson();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GET
	@Path("/search/{name}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchByName(@PathParam("name") String name) {
		try {
			if (name != null && !"".equals(name)) {
				return SpecialtyDAO.search(name).toJson();
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/add/{specialty}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean insert(@PathParam("specialty") String specialty) {
		if (specialty != null && !"".equals(specialty)) {
			Specialty special = Specialty.parseJson(specialty);
			return SpecialtyDAO.insert(special);
		}
		return false;
	}

	@PUT
	@Path("/update/{specialty}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean update(@PathParam("specialty") String specialty) {
		if (specialty != null && !"".equals(specialty)) {
			Specialty special = Specialty.parseJson(specialty);
			return SpecialtyDAO.update(special);
		}
		return false;
	}

	@DELETE
	@Path("/delete/{specialty}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public boolean delete(@PathParam("specialty") String specialty) {
		if (specialty != null && !"".equals(specialty)) {
			Specialty special = Specialty.parseJson(specialty);
			return SpecialtyDAO.delete(special);
		}
		return false;
	}

}
