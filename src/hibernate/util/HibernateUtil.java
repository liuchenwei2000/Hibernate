/**
 * 
 */
package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * HibernateUtil
 * <p>
 * 统一管理整个应用系统的SessionFactory。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public final class HibernateUtil {
	
	// 全局只有一个 SessionFactory 实例
	private static final SessionFactory instance;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			instance = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * 获取SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return instance;
	}
	
	private HibernateUtil() {
		// do nothing
	}
}
