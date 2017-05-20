package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Reservation;

public class ReservationUtil {
	public static Map<String, List<Reservation>> listAllReservation = new HashMap<String, List<Reservation>>();
	/*
	 * Khởi tạo dữ liệu Reservation ở đây bằng Contructor tương tự như phần
	 * Doctor
	 */
	
	public static boolean addReservation(String username, Reservation reservation){
		return listAllReservation.get(username).add(reservation);
	}
}
