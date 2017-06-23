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
	public String getMessage(@PathParam("id") String id) {
		if (id == null)
			return "Khong co";
		try {
			int i = Integer.parseInt(id);
			Message message = MessageDAO.getMessage(i);
			return message.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return "Sai dinh dang";
		}
	}
	
	@PUT
	@Path("/doctor/{idDoctor}/{idUser}/{content}")
	public String sendMessageOfDoctor(@PathParam("idDoctor") String idDoctor, @PathParam("idUser") String idUser,
			@PathParam("content") String content) {
		int idDoc = Integer.parseInt(idDoctor);
		int idUs = Integer.parseInt(idUser);
		Doctor doctor = DoctorDAO.getDoctor(idDoc);
		Message message = doctor.addMessage(idUs, content);
		return message.toJson();
	}
	
	@PUT
	@Path("/user/{idUser}/{idDoctor}/{content}")
	public String sendMessageOfUser(@PathParam("idUser") String idUser, @PathParam("idDoctor") String idDoctor,
			@PathParam("content") String content) {
		int idDoc = Integer.parseInt(idDoctor);
		int idUs = Integer.parseInt(idUser);
		Users user = UserDAO.getUsers(idUs);
		Message message = user.addMessage(idDoc, content);
		return message.toJson();
	}
}
