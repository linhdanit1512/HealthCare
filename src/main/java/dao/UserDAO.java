package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Users;
import util.HibernateUtils;

public class UserDAO extends ClassDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4200096323348870050L;

	public static List<Users> getAllUsers() {
		List<Users> users = new ArrayList<Users>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Users.class.getName() + " e  order by e.idUser asc";
			Query query = session.createQuery(hql);
			users = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return users;
	}

	public static Users getUsers(int id) {
		Users user = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + Users.class.getName() + " e where e.idUser =:user";
			Query query = session.createQuery(hql);
			query.setParameter("user", id);
			if (query.list().size() > 0)
				user = (Users) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return user;
	}
}
