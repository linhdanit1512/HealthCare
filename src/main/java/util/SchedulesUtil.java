package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Schedules;

public class SchedulesUtil {
	public static Map<String, List<Schedules>> listAllSchedules = new HashMap<String, List<Schedules>>();
	/*
	 * Khởi tạo dữ liệu Schedules ở đây bằng Contructor tương tự như phần
	 * Doctor
	 */
	
	public static boolean addSchedules(String username, Schedules schedules){
		return listAllSchedules.get(username).add(schedules);
	}
}
