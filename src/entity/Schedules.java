package entity;
// Generated May 8, 2017 8:56:39 AM by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import flexjson.JSONSerializer;

/**
 * Schedules generated by hbm2java
 */
@Entity
@XmlRootElement
public class Schedules implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454404002053670572L;
	@Id
	@GeneratedValue
	private int idSchedule;
	private String dates;
	private Integer startTime;
	private Integer stopTime;
	private String workspace;
	@ManyToMany()
	private Set<Doctor> doctors = new HashSet<>(0);

	public Schedules() {
	}

	public Schedules(int idSchedule, String dates) {
		this.idSchedule = idSchedule;
		this.dates = dates;
	}

	public Schedules(String dates, Integer startTime, Integer stopTime, String workspace) {
		super();
		this.dates = dates;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.workspace = workspace;
	}

	public Schedules(int idSchedule, String dates, Integer startTime, Integer stopTime, String workspace) {
		super();
		this.idSchedule = idSchedule;
		this.dates = dates;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.workspace = workspace;
	}

	public Schedules(int idSchedule, String dates, Integer startTime, Integer stopTime, String workspace,
			Set<Doctor> doctors) {
		this.idSchedule = idSchedule;
		this.dates = dates;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.workspace = workspace;
		this.doctors = doctors;
	}

	public int getIdSchedule() {
		return this.idSchedule;
	}

	@XmlElement
	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
	}

	public String getDates() {
		return this.dates;
	}

	@XmlElement
	public void setDates(String dates) {
		this.dates = dates;
	}

	public Integer getStartTime() {
		return this.startTime;
	}

	@XmlElement
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getStopTime() {
		return this.stopTime;
	}

	@XmlElement
	public void setStopTime(Integer stopTime) {
		this.stopTime = stopTime;
	}

	public String getWorkspace() {
		return this.workspace;
	}

	@XmlElement
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	@XmlElement
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public String getStartTimeClock() {
		return getTime(startTime);
	}

	public String getStopTimeClock() {
		return getTime(stopTime);
	}

	private String getTime(int time) {
		if (startTime <= 60)
			return startTime + "s";
		else if (startTime <= 3600)
			return (startTime / 60) + "m" + getTime(startTime % 60);
		else if (startTime <= 86400)
			return (startTime / 3600) + "h" + getTime(startTime % 3600);
		else
			return startTime / 86400 + "d" + getTime(startTime % 86400);
	}

	@Override
	public String toString() {
		return "Schedules " + getIdSchedule() + ":\t" + getDates() + " " + getStartTimeClock() + " - "
				+ getStopTimeClock() + "\t" + getWorkspace();
	}

	public String toJson() {
		JSONSerializer js = new JSONSerializer();
		return js.serialize(this);
	}
}
