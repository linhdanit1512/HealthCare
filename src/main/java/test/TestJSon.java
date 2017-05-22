package test;

import DAO.DoctorDAO;
import entity.Doctor;
import util.HibernateUtils;

public class TestJSon {
	public static void main(String[] args) {
		Doctor doctor = DoctorDAO.getDoctor(1);
		System.out.println("doctor " + doctor + "\n" + doctor.toJson());
		// Patient p = PatientDAO.getPatient(1);
		// System.out.println(p.toJson());
		HibernateUtils.shutdown();

		// String s = "{\"address\":\"Dong
		// Nai\",\"idClinic\":1,\"nameClinic\":\"Ccccccc\"}";
	}

}
