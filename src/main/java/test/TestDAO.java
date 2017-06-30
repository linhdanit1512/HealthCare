package test;

import DAO.DoctorDAO;
import entity.Reservation;
import util.HibernateUtils;

public class TestDAO {

	public static void main(String[] args) {
		// Clinic c = new Clinic("Dong Nai", "Ccccccc");
		// ClassDAO.insert(c);
//		System.out.println(DoctorDAO.getReservation(1));
//		 System.out.println(ClinicDAO.getClinic(1).getDoctors());
//		 System.out.println(ClinicDAO.getLastID());
//		System.out.println(ClinicDAO.getAllClinic() + "\n");
//		System.out.println(DoctorDAO.getAllDoctor() + "\n");
//		System.out.println(MessageDAO.getAllMessage() + "\n");
//		System.out.println(PatientDAO.getAllPatient() + "\n");
//		System.out.println(Schedules.toJsonList(ScheduleDAO.getAllSchedules()) + "\n");
//		System.out.println(SpecialtyDAO.getAllSpecialty() + "\n");
//		System.out.println(UserDAO.getAllUsers());
//		System.out.println(HibernateUtils.getSessionFactory());
//		System.out.println(DoctorDAO.getDoctor(1).toJson());
//		System.out.println(HibernateUtils.getSessionFactory());
		System.out.println(Reservation.toJsonList(DoctorDAO.getReservationUnchecked(1)));
//		System.out.println(DoctorDAO.login("nguyentu", "123"));
//		System.out.println(ClinicDAO.getClinicHasDoctor());
//		System.out.println(Reservation.toJsonList(DoctorDAO.getReservationChecked(1)));
		 HibernateUtils.shutdown();
	}
}
