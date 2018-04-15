package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Patient;
import util.HibernateUtils;

public class PatientDAO extends ClassDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4912715958064073104L;
	public static List<Patient> getAllPatient() {
		List<Patient> patients = new ArrayList<Patient>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Patient.class.getName() + " e  order by e.idPatient asc";
			Query query = session.createQuery(hql);
			patients = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return patients;
	}
	
	public static Patient getPatient(int id) {
		Patient patient = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Patient.class.getName() + " e where e.idPatient =:patient";
			Query query = session.createQuery(hql);
			query.setParameter("patient", id);
			if (query.list().size() > 0)
				patient = (Patient) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return patient;
	}

}
