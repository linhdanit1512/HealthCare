package dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Reservation;
import util.HibernateUtils;

public class ReservationDAO extends ClassDAO {

	private static final long serialVersionUID = 1L;

	public static Reservation getReservation(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Reservation.class.getName() + " e where e.idReservation=:id";
			Query<Reservation> query = session.createQuery(hql);
			query.setParameter("id", id);
			Reservation r = query.getSingleResult();
			session.getTransaction().commit();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
	
}
