package rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import DAO.DoctorDAO;
import DAO.MessageDAO;
import DAO.UserDAO;
import entity.Doctor;
import entity.Message;
import entity.Users;

/**
 * @author Nguyen Tu
 */

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessageService {

	@Path("/all")
	@GET
	public String getAllMessage() {
		try {
			List<Message> messages = MessageDAO.getAllMessage();
			return Message.toJsonList(messages);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public String getMessage(@PathParam("id") int id) {
		try {
			Message message = MessageDAO.getMessage(id);
			return message.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"" + Message.class.getName() + "\":null}";
		}
	}

	@PUT
	@Path("/conversation/doctor")
	public String sendMessageOfDoctor(@FormParam("idDoctor") int idDoctor, @FormParam("idUser") int idUser,
			@FormParam("content") String content, @Context HttpServletResponse servletResponse) {
		Doctor doctor = DoctorDAO.getDoctor(idDoctor);
		Message message = doctor.addMessage(idUser, content);
		return message.toJson();
	}

	@PUT
	@Path("/conversation/user")
	public String sendMessageOfUser(@FormParam("idUser") int idUser, @FormParam("idDoctor") int idDoctor,
			@FormParam("content") String content, @Context HttpServletResponse servletResponse) {
		Users user = UserDAO.getUsers(idUser);
		Message message = user.addMessage(idDoctor, content);
		return message.toJson();
	}
}
