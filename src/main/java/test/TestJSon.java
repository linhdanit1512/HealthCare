package test;

import DAO.DoctorDAO;
import entity.Doctor;
import util.HibernateUtils;

public class TestJSon {
	public static void main(String[] args) {
		Doctor doctor = DoctorDAO.getDoctor(1);
		doctor.setIdDoctor(5);
		doctor.setIsCheck(false);
		doctor.setUsername("linhdan001");
		doctor.setPassport("45686625941212");
		System.out.println("doctor regis: "+DoctorDAO.register(doctor.toJson()));
		// Patient p = PatientDAO.getPatient(1);
		// System.out.println(p.toJson());
		HibernateUtils.shutdown();
		
		

	}

}
