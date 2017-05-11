package test;

import DAO.ClinicDAO;

public class TestDAO {

	public static void main(String[] args) {
//		Clinic c = new Clinic("Dong Nai", "Ccccccc");
//		ClassDAO.insert(c);
//		System.out.println(ClinicDAO.getClinic(1).getDoctors());
		System.out.println(ClinicDAO.getLastID());
//		HibernateUtils.shutdown();
	}
}
