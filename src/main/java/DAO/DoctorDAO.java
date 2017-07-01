package DAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Doctor;
import entity.Message;
import entity.Reservation;
import entity.Schedules;
import util.HibernateUtils;

public class DoctorDAO extends ClassDAO {

	public static final Comparator<Schedules> COMPARATOR = new Comparator<Schedules>() {

		public int compare(Schedules o1, Schedules o2) {
			String thu = o1.getDates();
			String thu2 = o2.getDates();
			int s1 = 0, s2 = 0;
			if ("sunday".equals(thu.toLowerCase()))
				s1 = 1;
			else if ("monday".equals(thu.toLowerCase()))
				s1 = 2;
			else if ("tuesday".equals(thu.toLowerCase()))
				s1 = 3;
			else if ("wednesday".equals(thu.toLowerCase()))
				s1 = 4;
			else if ("thursday".equals(thu.toLowerCase()))
				s1 = 5;
			else if ("friday".equals(thu.toLowerCase()))
				s1 = 6;
			else if ("saturday".equals(thu.toLowerCase()))
				s1 = 7;

			if ("sunday".equals(thu2.toLowerCase().toLowerCase()))
				s2 = 1;
			else if ("monday".equals(thu2.toLowerCase()))
				s2 = 2;
			else if ("tuesday".equals(thu2.toLowerCase()))
				s2 = 3;
			else if ("wednesday".equals(thu2.toLowerCase()))
				s2 = 4;
			else if ("thursday".equals(thu2.toLowerCase()))
				s2 = 5;
			else if ("friday".equals(thu2.toLowerCase()))
				s2 = 6;
			else if ("saturday".equals(thu2.toLowerCase()))
				s2 = 7;

			if (s1 < s2) {
				return -2;
			} else if (s1 > s2) {
				return 2;
			} else if (s1 == s2) {
				if (o1.getStartTime() > o2.getStartTime())
					return 1;
				else if (o1.getStartTime() < o2.getStartTime())
					return -1;
				else
					return 0;
			}
			return 0;
		}
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 2360930720951306835L;

	public static Doctor login(String userName, String pass) {
		Doctor doctor = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName()
					+ " e where e.userName =:name and e.passwords =:pass and e.isCheck = true";
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("name", userName);
			query.setParameter("pass", pass);
			doctor = query.getSingleResult();
			if (checkDoctor(doctor)) {
				session.getTransaction().commit();
				return doctor;
			} else {
				session.getTransaction().commit();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	private static boolean checkDoctor(Doctor doctor) {
		if (doctor == null)
			return false;
		if (doctor.getPassActive())
			return true;
		else {
			Date date = new Date();
			/*
			 * thời gian từ lúc thay đổi tới hiện tại
			 */
			long x = date.getTime() - doctor.getTimeChange().getTime();
			if (x <= (60 * 20 * 1000)) {
				doctor.setPassActive(true);
				doctor.setOldPassword(doctor.getPasswords());
				update(doctor);
				return true;
			} else {
				doctor.setPassActive(true);
				if (doctor.getOldPassword().equals(doctor.getPasswords())) {
					doctor.setPasswords(doctor.getOldPassword());
					update(doctor);
					return true;
				}
				doctor.setPasswords(doctor.getOldPassword());
				update(doctor);
				return false;
			}
		}
	}

	/**
	 * kiem tra dang ki co thanh cong hay ko.
	 * 
	 * dieu kien dang ky thanh cong:
	 * 
	 * + id khong duoc trung
	 * 
	 * + userName ko duoc trung
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
						+ " e where e.idDoctor =:id or e.userName=:userName or e.passport =:passport or e.email=:email";
				Query<Doctor> query = session.createQuery(hql);
				query.setParameter("id", doctor.getIdDoctor());
				query.setParameter("userName", doctor.getUserName());
				query.setParameter("passport", doctor.getPassport());
				query.setParameter("email", doctor.getEmail());
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

	public static List<Doctor> getAllDoctor() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e  order by e.idDoctor asc";
			Query<Doctor> query = session.createQuery(hql);
			doctors = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctors;
	}

	public static List<Doctor> getDoctorUncheck() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.isCheck=false order by e.idDoctor asc";
			Query<Doctor> query = session.createQuery(hql);
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
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("doctor", id);
			doctor = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	public static Doctor getDoctorEmail(String email) {
		Doctor doctor = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.email =:email";
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("email", email);
			doctor = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	public static Doctor getDoctorPassport(String passport) {
		Doctor doctor = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.passport =:passport";
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("passport", passport);
			doctor = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return doctor;
	}

	public static List<Schedules> getSchedule(int idDoctor) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Doctor.class.getName() + " e where e.idDoctor =:doctor";
			Query<Doctor> query = session.createQuery(hql);
			query.setParameter("doctor", idDoctor);
			List<Schedules> list = new ArrayList<Schedules>();
			if (query.list() != null && query.list().size() > 0) {
				Doctor doctor = query.uniqueResult();
				if (doctor != null) {
					Set<Schedules> set = doctor.getScheduleses();
					for (Schedules s : set) {
						list.add(s);
					}
				}
			}
			list.sort(COMPARATOR);
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public static List<Reservation> getReservation(int idDoctor) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Reservation.class.getName() + " e where e.doctor.idDoctor =:doctor";
			Query<Reservation> query = session.createQuery(hql);
			query.setParameter("doctor", idDoctor);
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
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Reservation.class.getName()
					+ " e where e.doctor.idDoctor =:doctor and e.isConfirm=false";
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

	public static List<Reservation> getReservationChecked(int id) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Reservation.class.getName()
					+ " e where e.doctor.idDoctor =:doctor and e.isConfirm=true";
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

	public static List<Message> getMessages(int id) {
		Doctor doctor = getDoctor(id);
		if (doctor != null) {
			List<Message> result = new ArrayList<Message>();
			for (Message r : doctor.getMessages()) {
				result.add(r);
			}
			return result;
		}
		return null;
	}

	public static int nextID() {
		int result = 0;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "select max(idDoctor) from " + Doctor.class.getName();
			Query<Integer> query = session.createQuery(hql);
			result = (int) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return result + 1;
	}
}
