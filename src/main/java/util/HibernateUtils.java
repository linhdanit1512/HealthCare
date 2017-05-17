package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.service.ServiceRegistry;

/**
 * 
 * @author LinhDan
 * 
 */
public class HibernateUtils {

	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * @link http://www.roseindia.net/hibernate/hibernate5/hibernate-5-buildsessionfactory.shtml
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			return metaData.getSessionFactoryBuilder().build();
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		if (getSessionFactory() != null)
			getSessionFactory().close();
	}

}
