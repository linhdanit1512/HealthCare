package test;

import DAO.ClinicDAO;
import DAO.DoctorDAO;
import DAO.MessageDAO;
import DAO.PatientDAO;
import DAO.ScheduleDAO;
import DAO.SpecialtyDAO;
import DAO.UserDAO;
import util.HibernateUtils;

public class TestDAO {

	public static void main(String[] args) {
		// Clinic c = new Clinic("Dong Nai", "Ccccccc");
		// ClassDAO.insert(c);
		// System.out.println(ClinicDAO.getClinic(1).getDoctors());
		// System.out.println(ClinicDAO.getLastID());
		System.out.println(ClinicDAO.getAllClinic() + "\n");
		System.out.println(DoctorDAO.getAllDoctor() + "\n");
		System.out.println(MessageDAO.getAllMessage() + "\n");
		System.out.println(PatientDAO.getAllPatient() + "\n");
		System.out.println(ScheduleDAO.getAllSchedules() + "\n");
		System.out.println(SpecialtyDAO.getAllSpecialty() + "\n");
		System.out.println(UserDAO.getAllUsers());
		 HibernateUtils.shutdown();
	}
}
