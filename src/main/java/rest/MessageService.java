package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

@Path("/message")
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
	@Path("/doctor/{idDoctor}/{idUser}/{content}")
	public String sendMessageOfDoctor(@PathParam("idDoctor") int idDoctor, @PathParam("idUser") int idUser,
			@PathParam("content") String content) {
		Doctor doctor = DoctorDAO.getDoctor(idDoctor);
		Message message = doctor.addMessage(idUser, content);
		return message.toJson();
	}

	@PUT
	@Path("/user/{idUser}/{idDoctor}/{content}")
	public String sendMessageOfUser(@PathParam("idUser") int idUser, @PathParam("idDoctor") int idDoctor,
			@PathParam("content") String content) {
		Users user = UserDAO.getUsers(idUser);
		Message message = user.addMessage(idDoctor, content);
		return message.toJson();
	}
}
