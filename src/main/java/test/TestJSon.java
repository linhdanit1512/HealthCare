package test;

import DAO.DoctorDAO;
import entity.Doctor;
import entity.Schedules;
import util.HibernateUtils;

public class TestJSon {
	public static void main(String[] args) {
		// Doctor doctor = DoctorDAO.getDoctor(1);
		// doctor.setIdDoctor(5);
		// doctor.setIsCheck(false);
		// doctor.setUsername("linhdan001");
		// doctor.setPassport("45686625941212");
		// System.out.println("doctor regis:
		// "+DoctorDAO.register(doctor.toJson()));
		// // Patient p = PatientDAO.getPatient(1);
		// // System.out.println(p.toJson());

		Doctor doctor = DoctorDAO.getDoctor(1);

		String json = Schedules.toJsonList(doctor.getScheduleses());
		Schedules.parseJsonList(json);
		// String s =
		// "{\"idDoctor\":5,\"specialty\":{\"idSpecialty\":10,\"nameSpecialty\":\"abc\"},\"passwords\":\"123456\",\"email\":\"test@test.com\",\"phone\":\"01234567892\",\"passport\":\"abc\",\"degree\":\"abc\",\"experience\":123,\"address\":\"abc\",\"timeCreate\":1498348800000,\"isCheck\":false}";
		// System.out.println(Doctor.parseJson(s));
		HibernateUtils.shutdown();
	}

}
