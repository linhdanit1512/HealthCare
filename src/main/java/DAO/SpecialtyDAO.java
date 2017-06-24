package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Specialty;
import util.HibernateUtils;

public class SpecialtyDAO extends ClassDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7825085400737051456L;

	public static List<Specialty> getAllSpecialty() {
		List<Specialty> specialtys = new ArrayList<Specialty>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Specialty.class.getName() + " e  order by e.idSpecialty asc";
			Query<Specialty> query = session.createQuery(hql);
			specialtys = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return specialtys;
	}

	public static Specialty getSpecialtyByName(String nameSpacialty) {
		Specialty specialty = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Specialty.class.getName() + " e where e.nameSpecialty =:specialty";
			Query<Specialty> query = session.createQuery(hql);
			query.setParameter("specialty", nameSpacialty);
			if (query.list().size() > 0)
				specialty = query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return specialty;
	}

	public static Specialty getSpecialty(int id) {
		Specialty specialty = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Specialty.class.getName() + " e where e.idSpecialty =:specialty";
			Query<Specialty> query = session.createQuery(hql);
			query.setParameter("specialty", id);
			if (query.list().size() > 0)
				specialty = query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return specialty;
	}
}
