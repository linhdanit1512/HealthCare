package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.DoctorDAO;
import entity.Reservation;
import entity.Users;

public class ReservationUtil {
	public static Map<String, List<Reservation>> listAllReservation = new HashMap<String, List<Reservation>>();
	/*
	 * Khởi tạo dữ liệu Reservation ở đây bằng Contructor tương tự như phần
	 * Doctor
	 */

	public ReservationUtil() {
		Reservation r1 = new Reservation(1, DoctorDAO.getDoctor(3),
				new Users("nguyentued@gmail.com", "123456", new Date()), new Date(), false);
		Reservation r2 = new Reservation(1, DoctorDAO.getDoctor(1),
				new Users("nhanvuongngocbao@gmail.com", "123456", new Date()), new Date(), false);
		List<Reservation> list1 = new ArrayList<Reservation>();
		List<Reservation> list2 = new ArrayList<Reservation>();
		list1.add(r1);
		list2.add(r2);
		listAllReservation.put("hoanghung", list1);
		listAllReservation.put("linhdan", list2);
	}

	public static boolean addReservation(String username, Reservation reservation) {
		return listAllReservation.get(username).add(reservation);
	}
}
