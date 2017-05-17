package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Message;
import util.HibernateUtils;

public class MessageDAO extends ClassDAO{

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
			Query query = session.createQuery(hql);
			query.setParameter("message", id);
			query.setMaxResults(1);
			if (query.list().size() > 0)
				message = (Message) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return message;
	}
}
