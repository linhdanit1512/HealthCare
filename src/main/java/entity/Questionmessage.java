package entity;
// Generated May 8, 2017 8:56:39 AM by Hibernate Tools 5.2.1.Final

import java.io.IOException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Questionmessage generated by hbm2java
 */
@Entity
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Questionmessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3816637729108485148L;
	@Id
	@GeneratedValue
	private int idMessage;
	@OneToOne()
	private Message message;
	@ManyToOne
	private Specialty specialty;

	public Questionmessage() {
	}

	public Questionmessage(Message message) {
		this.message = message;
	}

	public Questionmessage(Message message, Specialty specialty) {
		this.message = message;
		this.specialty = specialty;
	}

	public int getIdMessage() {
		return this.idMessage;
	}

	@XmlElement
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public Message getMessage() {
		return this.message;
	}

	@XmlElement
	public void setMessage(Message message) {
		this.message = message;
	}

	public Specialty getSpecialty() {
		return this.specialty;
	}

	@XmlElement
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Questionmessage parseJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Questionmessage question = mapper.readValue(json, Questionmessage.class);
			return question;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}public static List<Questionmessage> parseJsonList(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Questionmessage> list = mapper.readValue(json,
					mapper.getTypeFactory().constructCollectionType(List.class, Questionmessage.class));
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toJsonList(List<Questionmessage> list) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (Questionmessage d : list) {
				sb.append(d.toJson());
				sb.append(",");
			}
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
