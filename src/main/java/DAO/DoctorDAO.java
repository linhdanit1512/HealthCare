package DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Doctor;
import entity.Reservation;
import entity.Schedules;
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
			String hql = "from " + Doctor.class.getName() + " e where e.username =:name and e.passwords =:pass and e.isCheck = true";
			Query query = session.createQuery(hql);
			query.setParameter("name", username);
			query.setParameter("pass", pass);
			if (query.list().size() > 0)
				doctor = (Doctor) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	/**
	 * kiem tra dang ki co thanh cong hay ko.
	 * 
	 * dieu kien dang ky thanh cong:
	 * 
	 * + id khong duoc trung
	 * 
	 * + username ko duoc trung
	 * 
	 * + CMND ko duoc trung
	 * 
	 * @param doctor
	 * @return
	 */
	public static boolean register(Doctor doctor) {
		if (doctor != null) {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			try {
				List<Doctor> list = new ArrayList<Doctor>();
				session.getTransaction().begin();

				String hql = "from " + Doctor.class.getName()
						+ " e where e.idDoctor =:id or e.username=:username or e.passport =:passport";
				Query query = session.createQuery(hql);
				query.setParameter("id", doctor.getIdDoctor());
				query.setParameter("username", doctor.getUsername());
				query.setParameter("passport", doctor.getPassport());

				list.addAll(query.list());
				session.getTransaction().commit();
				if (list.size() > 0)
					return false;
				else
					return true;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}
	
	public static boolean register(String jsonDoctor){
		if (jsonDoctor != null) {
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			try {
				Doctor doctor = Doctor.parseJson(jsonDoctor);
				List<Doctor> list = new ArrayList<Doctor>();
				session.getTransaction().begin();

				String hql = "from " + Doctor.class.getName()
						+ " e where e.idDoctor =:id or e.username=:username or e.passport =:passport";
				Query query = session.createQuery(hql);
				query.setParameter("id", doctor.getIdDoctor());
				query.setParameter("username", doctor.getUsername());
				query.setParameter("passport", doctor.getPassport());

				list.addAll(query.list());
				session.getTransaction().commit();
				if (list.size() > 0)
					return false;
				else
					return true;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getDoctor(1).toString());
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
			doctor = (Doctor) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	public static Set<Schedules> getSchedule(int idDoctor) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.idDoctor =:doctor";
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("doctor", idDoctor);
			Set<Schedules> list = new HashSet<Schedules>();
			if (query.list() != null && query.list().size() > 0) {
				Doctor doctor = (Doctor) query.uniqueResult();
				if (doctor != null)
					list = doctor.getScheduleses();
			}
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public static List<Reservation> getReservation(int id) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Reservation.class.getName() + " e where e.doctor.idDoctor =:doctor";
			Query<Reservation> query = session.createQuery(hql);
			query.setParameter("doctor", id);
			List<Reservation> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public static List<Reservation> getReservationUnchecked(int id) {
		List<Reservation> list = getReservation(id);
		List<Reservation> result = new ArrayList<Reservation>();
		for (Reservation r : list) {
			if (!r.getIsConfirm()) {
				result.add(r);
			}
		}
		return result;
	}

	public static List<Reservation> getReservationChecked(int id) {
		List<Reservation> list = getReservation(id);
		List<Reservation> result = new ArrayList<Reservation>();
		for (Reservation r : list) {
			if (r.getIsConfirm()) {
				result.add(r);
			}
		}
		return result;
	}

}
