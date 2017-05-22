package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import entity.Clinic;
import util.HibernateUtils;

public class ClinicDAO extends ClassDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6092174994731366061L;

	public static List<Clinic> getAllClinic() {
		List<Clinic> clinics = new ArrayList<Clinic>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Clinic.class.getName() + " e  order by e.idClinic asc";
			Query<Clinic> query = session.createQuery(hql);
			clinics = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return clinics;
	}
	
	public static Clinic getClinic(int id) {
		Clinic clinic = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Clinic.class.getName() + " e where e.idClinic =:clinic";
			Query<Clinic> query = session.createQuery(hql);
			query.setParameter("clinic", id);
			query.setMaxResults(1);
			if (query.list().size() > 0)
				clinic = query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return clinic;
	}
	
}
