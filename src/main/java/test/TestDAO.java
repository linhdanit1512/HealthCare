package test;

import DAO.DoctorDAO;
import entity.Schedules;
import util.HibernateUtils;

public class TestDAO {

	public static void main(String[] args) {
		// Clinic c = new Clinic("Dong Nai", "Ccccccc");
		// ClassDAO.insert(c);
		// System.out.println(ClinicDAO.getClinic(1).getDoctors());
		// System.out.println(ClinicDAO.getLastID());
//		System.out.println(ClinicDAO.getAllClinic() + "\n");
//		System.out.println(DoctorDAO.getAllDoctor() + "\n");
//		System.out.println(MessageDAO.getAllMessage() + "\n");
//		System.out.println(PatientDAO.getAllPatient() + "\n");
//		System.out.println(ScheduleDAO.getAllSchedules() + "\n");
//		System.out.println(SpecialtyDAO.getAllSpecialty() + "\n");
//		System.out.println(UserDAO.getAllUsers());
		System.out.println(Schedules.toJsonList(DoctorDAO.getSchedule(1)));
//		System.out.println(Doctor.toJsonList(DoctorDAO.getAllDoctor()));
//		System.out.println(HibernateUtils.getSessionFactory());
		 HibernateUtils.shutdown();
	}
}
