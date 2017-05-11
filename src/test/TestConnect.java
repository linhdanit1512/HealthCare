package test;

import util.HibernateUtils;

public class TestConnect {
	public static void main(String[] args) {
		System.out.println(HibernateUtils.getSessionFactory());
		HibernateUtils.shutdown();
	}
}
