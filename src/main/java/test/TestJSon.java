package test;

import DAO.ClinicDAO;
import DAO.DoctorDAO;
import entity.Clinic;
import entity.Doctor;

public class TestJSon {
	public static void main(String[] args) {
		Clinic clinic = ClinicDAO.getClinic(1);
		Doctor doctor = DoctorDAO.getDoctor(1);
		System.out.println(doctor.toJson());
		System.out.println(clinic.toJson());

		String s = "{\"address\":\"Dong Nai\",\"class\":\"entity.Clinic\",\"idClinic\":1,\"nameClinic\":\"Ccccccc\"}";
		// System.out.println(convert(s));
	}

}
