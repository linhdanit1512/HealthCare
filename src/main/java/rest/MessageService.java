package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.MessageDAO;
import entity.Message;

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
}
