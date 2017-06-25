package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Schedules;
import util.HibernateUtils;

public class ScheduleDAO extends ClassDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -295671412743912122L;

	public static List<Schedules> getAllSchedules() {
		List<Schedules> scheduless = new ArrayList<Schedules>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Schedules.class.getName() + " e  order by e.idSchedule asc";
			Query query = session.createQuery(hql);
			scheduless = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return scheduless;
	}

	public static Schedules getSchedules(int id) {
		Schedules schedules = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Schedules.class.getName() + " e where e.idSchedule =:schedule";
			Query query = session.createQuery(hql);
			query.setParameter("schedule", id);
			if (query.list().size() > 0)
				schedules = (Schedules) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return schedules;
	}

	public static Schedules getSchedules(String dates, int startTime, int stopTime, String workspace) {
		Schedules schedules = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Schedules.class.getName()
					+ " e where e.dates =:dates and e.startTime=:startTime and e.stopTime=:stopTime and e.workspace=:workspace";
			Query<Schedules> query = session.createQuery(hql);
			query.setParameter("dates", dates);
			query.setParameter("startTime", startTime);
			query.setParameter("stopTime", stopTime);
			query.setParameter("workspace", workspace);
			if (query.list().size() > 0)
				schedules = query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return schedules;
	}
}
