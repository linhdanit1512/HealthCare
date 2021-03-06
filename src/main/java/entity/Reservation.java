package entity;
// Generated May 8, 2017 8:56:39 AM by Hibernate Tools 5.2.1.Final

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Reservation generated by hbm2java
 */
@Entity
@XmlRootElement
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Reservation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5308229041711027457L;
	@Id
	@GeneratedValue
	private int idReservation;
	@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private Users users;
	// @Field(index = Index.YES, analyze=Analyze.NO, store = Store.YES)
	// @DateBridge(resolution = Resolution.DAY)
	private Date dates;
	private Boolean isConfirm;

	public Reservation() {
	}

	public Reservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Reservation(Doctor doctor, Users users, Date dates, Boolean isConfirm) {
		super();
		this.doctor = doctor;
		this.users = users;
		this.dates = dates;
		this.isConfirm = isConfirm;
	}

	public Reservation(int idReservation, Doctor doctor, Users users, Date dates, Boolean isConfirm) {
		this.idReservation = idReservation;
		this.doctor = doctor;
		this.users = users;
		this.dates = dates;
		this.isConfirm = isConfirm;
	}

	public int getIdReservation() {
		return this.idReservation;
	}

	@XmlElement
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	@XmlElement
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Users getUsers() {
		return this.users;
	}

	@XmlElement
	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getDates() {
		return this.dates;
	}

	@XmlElement
	public void setDates(Date dates) {
		this.dates = dates;
	}

	public Boolean getIsConfirm() {
		return this.isConfirm;
	}

	@XmlElement
	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservation [idReservation=");
		builder.append(idReservation);
		builder.append(", doctor=");
		builder.append(doctor);
		builder.append(", users=");
		builder.append(users);
		builder.append(", dates=");
		builder.append(dates);
		builder.append(", isConfirm=");
		builder.append(isConfirm);
		builder.append("]");
		return builder.toString();
	}

	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			return "{\"" + Reservation.class.getName() + "\": null}";
		}
	}

	public static Reservation parseJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Reservation r = mapper.readValue(json, Reservation.class);
			return r;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Reservation> parseJsonList(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = json.trim();
			if (json.startsWith("{\"reservationList\":")) {
				json = json.substring(json.indexOf(":") + 1, json.length() - 1);
			}
			List<Reservation> list = mapper.readValue(json,
					mapper.getTypeFactory().constructCollectionType(List.class, Reservation.class));
			return list;
		} catch (IOException e) {
			return null;
		}
	}

	public static String toJsonList(List<Reservation> list) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("{\"reservationList\":[");
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					sb.append(",");
				sb.append(list.get(i).toJson());
			}
			sb.append("]}");
			return sb.toString();
		} catch (Exception e) {
			return "{\"reservationList\":null}";
		}
	}
}
