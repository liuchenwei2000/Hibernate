/**
 * 
 */
package hibernate.configure;

import hibernate.PersonVO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Configurationʾ��
 * <p>
 * Configuration �������Hibernate��������Ϣ���ؼ����԰������ݿ�URL���û��������롢JDBC������ȵȡ�
 * ��Щ���Կ�����Hibernate�����ļ���hibernate.cfg.xml��hibernate.properties���м����趨��
 * <p>
 * Configuration ʵ�������������Ϣ����Sessionfactoryʵ����һ��������ϣ����������ض���������Ϣ��
 * ���Ӧ������Ҫ���ʶ�����ݿ⣬�������ÿ�����ݿ⣬�ֱ�Ϊ�䴴����Ӧ��SessionFactoryʵ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��17��
 */
public class ConfigurationTest {
	
	/**
	 * ͨ��XML�����ļ�����SessionFactory
	 */
	public static SessionFactory createSessionFactoryFromXML() {
		// Hibernate 4.x֮ǰ�汾��SessionFactory������ʽ����
		// return new Configuration().configure().buildSessionFactory();
		
		// ��4.x�汾��ʼ����Ҫʹ���������ִ���SessionFactory�ķ�ʽ
		/*
		 * Hibernate���Զ��ڵ�ǰ��classpath����Ѱhibernate.cfg.xml�ļ���������ص��ڴ��У���Ϊ���������Ļ������á�
		 */
		Configuration configuration = new Configuration().configure();
		
		// �������ʹ��Ĭ�ϵ�hibernate.cfg.xml�ļ���Ϊ�����ļ���Ҳ����ָ�������ļ���
		// Configuration configuration = new Configuration().configure(new File("C:\\myconfig.xml"));

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * ͨ��properties�ļ�����SessionFactory
	 */
	public static SessionFactory createSessionFactoryFromProperties() {
		/*
		 * ���ѡ����properties��ʽ�������ļ�������ȱ����Ӧ��������Ŀ����ʱ�����Ҫͨ��������м���.
		 */
		Configuration configuration = new Configuration().addFile(
				"hibernate/PersonVO.hbm.xml").addClass(PersonVO.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
