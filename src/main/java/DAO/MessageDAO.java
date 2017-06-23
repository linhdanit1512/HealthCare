package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Message;
import util.HibernateUtils;

public class MessageDAO extends ClassDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231661774547327170L;

	public static List<Message> getAllMessage() {
		List<Message> messages = new ArrayList<Message>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Message.class.getName() + " e  order by e.idMessage asc";
			Query query = session.createQuery(hql);
			messages = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return messages;
	}

	public static Message getMessage(int id) {
		Message message = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Message.class.getName() + " e where e.idMessage =:message";
			Query<Message> query = session.createQuery(hql);
			query.setParameter("message", id);
			message = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return message;
	}

	public static List<Message> getMessageByDoctor(int idDoctor) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Message.class.getName() + " e where e.doctor.idDoctor=:id order by e.times desc";
			Query<Message> query = session.createQuery(hql);
			query.setParameter("id", idDoctor);
			List<Message> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public static List<Message> getMessageByDoctorAndUser(int idDoctor, int idUser) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Message.class.getName()
					+ " e where e.doctor.idDoctor=:idDoctor and e.users.idUser=:idUser order by e.times desc";
			Query<Message> query = session.createQuery(hql);
			query.setParameter("idDoctor", idDoctor);
			query.setParameter("idUser", idUser);
			List<Message> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}
