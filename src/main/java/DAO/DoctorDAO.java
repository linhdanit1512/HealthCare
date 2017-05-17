package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Doctor;
import util.HibernateUtils;

public class DoctorDAO extends ClassDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2360930720951306835L;

	public static Doctor login(String username, String pass) {
		Doctor doctor = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.username =:name and e.passwords =:pass";
			Query query = session.createQuery(hql);
			query.setParameter("name", username);
			query.setParameter("pass", pass);
			query.setMaxResults(1);
			if (query.list().size() > 0)
				doctor = (Doctor) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	public static List<Doctor> getAllDoctor() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e  order by e.idDoctor asc";
			Query query = session.createQuery(hql);
			doctors = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctors;
	}

	public static Doctor getDoctor(int id) {
		Doctor doctor = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.idDoctor =:doctor";
			Query query = session.createQuery(hql);
			query.setParameter("doctor", id);
			query.setMaxResults(1);
			if (query.list().size() > 0)
				doctor = (Doctor) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

}
