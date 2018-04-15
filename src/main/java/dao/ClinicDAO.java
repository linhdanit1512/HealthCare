package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import entity.Clinic;
import util.HibernateUtils;

public class ClinicDAO extends ClassDAO {

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
			if (query.list().size() > 0)
				clinic = query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return clinic;
	}

	public static List<Clinic> getClinicByName(String name) {
		List<Clinic> clinic = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Clinic.class.getName() + " e where e.nameClinic like '%" + name + "%'";
			Query<Clinic> query = session.createQuery(hql);
			if (query.list().size() > 0)
				clinic = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return clinic;
	}

	public static List<Clinic> getClinicByAddress(String address) {
		List<Clinic> clinic = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Clinic.class.getName() + " e where e.address like '%" + address + "%'";
			Query<Clinic> query = session.createQuery(hql);
			clinic = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return clinic;
	}

	public static Clinic getClinicNameAndAddress(String name, String address) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Clinic.class.getName() + " e where e.address=:address and e.nameClinic=:name";
			Query<Clinic> query = session.createQuery(hql);
			query.setParameter("address", address);
			query.setParameter("name", name);
			if (query.list() != null && query.list().size() > 0) {
				Clinic clinic = query.list().get(0);
				session.getTransaction().commit();
				return clinic;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public static int getClinicNonDoctor() {
		List<Clinic> list = getAllClinic();
		int count = 0;
		if (list != null)
			for (Clinic clinic : list) {
				if (clinic.getDoctors() == null || clinic.getDoctors().size() == 0) {
					count++;
				}
			}
		return count;
	}

	public static int getClinicHasDoctor() {
		List<Clinic> list = getAllClinic();
		int count = 0;
		if (list != null)
			for (Clinic clinic : list) {
				if (clinic.getDoctors() != null && clinic.getDoctors().size() != 0) {
					count++;
				}
			}
		return count;
	}

}
