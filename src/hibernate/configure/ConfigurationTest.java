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
 * Configuration示例
 * <p>
 * Configuration 负责管理Hibernate的配置信息，关键属性包括数据库URL、用户名、密码、JDBC驱动类等等。
 * 这些属性可以在Hibernate配置文件（hibernate.cfg.xml或hibernate.properties）中加以设定。
 * <p>
 * Configuration 实例会根据配置信息构造Sessionfactory实例，一旦构造完毕，即被赋予特定的配置信息。
 * 如果应用中需要访问多个数据库，可以针对每个数据库，分别为其创建对应的SessionFactory实例。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月17日
 */
public class ConfigurationTest {
	
	/**
	 * 通过XML配置文件创建SessionFactory
	 */
	public static SessionFactory createSessionFactoryFromXML() {
		// Hibernate 4.x之前版本的SessionFactory创建方式如下
		// return new Configuration().configure().buildSessionFactory();
		
		// 从4.x版本开始，需要使用下面这种创建SessionFactory的方式
		/*
		 * Hibernate会自动在当前的classpath中搜寻hibernate.cfg.xml文件并将其加载到内存中，作为后续操作的基础配置。
		 */
		Configuration configuration = new Configuration().configure();
		
		// 如果不想使用默认的hibernate.cfg.xml文件作为配置文件，也可以指定配置文件名
		// Configuration configuration = new Configuration().configure(new File("C:\\myconfig.xml"));

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * 通过properties文件创建SessionFactory
	 */
	public static SessionFactory createSessionFactoryFromProperties() {
		/*
		 * 如果选用了properties形式的配置文件，由于缺乏相应的配置条目，这时候就需要通过编码进行加载.
		 */
		Configuration configuration = new Configuration().addFile(
				"hibernate/PersonVO.hbm.xml").addClass(PersonVO.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
