package test;

import java.util.HashMap;

import DAO.ClinicDAO;
import DAO.DoctorDAO;
import entity.Clinic;
import entity.Doctor;
import flexjson.JSONTokener;

public class TestJSon {
	public static void main(String[] args) {
		Clinic clinic = ClinicDAO.getClinic(1);
		Doctor doctor = DoctorDAO.getDoctor(1);
		System.out.println(doctor.toJson());
		System.out.println(clinic.toJson());

		// String s = "{\"address\":\"Dong
		// Nai\",\"class\":\"entity.Clinic\",\"idClinic\":1,\"nameClinic\":\"Ccccccc\"}";
		// System.out.println(convert(s));
	}

	public static Clinic convert(String json) {
		JSONTokener s = new JSONTokener(json);
		HashMap<String, Object> hashMap = (HashMap<String, Object>) s.nextValue();
		int id = (int) hashMap.get("idClinic");
		String add = (String) hashMap.get("address");
		String name = (String) hashMap.get("nameClinic");
		Clinic clinic = new Clinic(id, add, name);
		return clinic;
	}
}
