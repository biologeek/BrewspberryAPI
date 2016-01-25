package net.brewspberry.util;

import net.brewspberry.business.beans.Actioner;
import net.brewspberry.business.beans.Biere;
import net.brewspberry.business.beans.Brassin;
import net.brewspberry.business.beans.Etape;
import net.brewspberry.business.beans.Houblon;
import net.brewspberry.business.beans.Ingredient;
import net.brewspberry.business.beans.Levure;
import net.brewspberry.business.beans.Malt;
import net.brewspberry.business.beans.Temperature;
import net.brewspberry.business.beans.TemperatureMeasurement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static ServiceRegistry serviceRegistry;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory;

	

	static {
		try {
			System.out.println("Trying to configure SessionFactory");
			sessionFactory = configureSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}
	
	public static SessionFactory configureSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure()
				.addAnnotatedClass(Malt.class)
				.addAnnotatedClass(Houblon.class)
				.addAnnotatedClass(Ingredient.class)
				.addAnnotatedClass(Etape.class)
				.addAnnotatedClass(Biere.class)
				.addAnnotatedClass(Brassin.class)
				.addAnnotatedClass(Temperature.class)
				.addAnnotatedClass(TemperatureMeasurement.class)
				.addAnnotatedClass(Levure.class)
				.addAnnotatedClass(Actioner.class);
			
			sessionFactory = configuration.buildSessionFactory();

		} catch (Exception e) {
			System.out.append("** Exception in SessionFactory **");
			e.printStackTrace();
		}
		return sessionFactory;
	}


	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = configureSessionFactory();
		return sessionFactory;
	}

	public static Session getSession() throws HibernateException {
		Session session = getSessionFactory().openSession();

		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}

		return session;
	}

	public static void rebuildSessionFactory() {
		try {
			sessionFactory = configureSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
}