package restful.healthCare.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import restful.healthCare.entity.Doctor;

public class DoctorUtil {
	public static Map<String, Doctor> listAllDoctors = new HashMap<String, Doctor>();

	public DoctorUtil() {
		Doctor doctor = new Doctor(1, null, null, "A", "Nguyễn Văn A", "123", "0123456789", "passport1",
				"Bác sĩ cao cấp", 22, "address A", "Hồ Chí Minh", new Date(), true, null, null, null);
		Doctor doctor2 = new Doctor(2, null, null, "B", "Nguyễn Văn B", "123", "0123456788", "passport2",
				"Bác sĩ thực tập", 1, "address B", "Hà Nội", new Date(), true, null, null, null);
		registerDoctor(doctor);
		registerDoctor(doctor2);
	}

	/**
	 * Register Doctor account
	 * 
	 * @param doctor
	 *            information of Doctor
	 * @return Doctor
	 */
	public static Doctor registerDoctor(Doctor doctor) {
		if (!listAllDoctors.containsKey(doctor.getUsername())) {
			listAllDoctors.put(doctor.getUsername(), doctor);
			return listAllDoctors.get(doctor.getUsername());
		}
		return null;
	}

	/**
	 * Doctor login to system
	 * 
	 * @param username
	 *            username of Doctor
	 * @param password
	 *            password of Doctor
	 * @return Doctor if login success, null if not
	 */
	public static Doctor loginDoctor(String username, String password) {
		if (listAllDoctors.containsKey(username)) {
			if (listAllDoctors.get(username).getPasswords().equals(password)) {
				return listAllDoctors.get(username);
			}
		}
		return null;
	}
}
