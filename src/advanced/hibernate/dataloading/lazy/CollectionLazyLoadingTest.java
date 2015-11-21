/**
 * 
 */
package hibernate.dataloading.lazy;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * 集合的延迟加载示例
 * <p>
 * Hibernate延迟加载机制中，关于集合的延迟加载特性意义最为重大。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月17日
 */
public class CollectionLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * 如果要做到集合的延迟加载，就需要在加载Student对象时只针对其本身的属性，而当需要获取Student对象
			 * 所关联的Contact信息时（如执行student.getContacts），才真正从数据库中加载Contact数据并返回。
			 * 
			 * 输出如下：
			 * Hibernate: select student0_.pk_student as pk_stude1_1_0_, student0_.name as name2_1_0_ from tb_students student0_ where student0_.pk_student=?
			 * 【after loading Student,but before student.getContacts()】
			 * Hibernate: select contacts0_.pk_student as pk_stude4_1_0_, contacts0_.pk_contact as pk_conta1_0_0_, contacts0_.pk_contact as pk_conta1_0_1_, contacts0_.address as address2_0_1_, contacts0_.phone as phone3_0_1_, contacts0_.pk_student as pk_stude4_0_1_ from tb_contacts contacts0_ where contacts0_.pk_student=? order by contacts0_.address asc
			 * Student [name=Tom Green, contacts=[Contact [address=Beijing China, phone=010-287356772], Contact [address=Washington D.C USA, phone=123-456-789]]]
			 * 
			 * 可见第一次查询只是把Student自身的信息查了回来，而关联的Contact信息等到调用student.getContacts时才发起真正的查询。
			 */
			Student student = (Student) newSession.load(Student.class, 1L);
			System.out.println("【after loading Student,but before student.getContacts()】");
			System.out.println(student);
			
			Session newSession2 = sessionFactory.openSession();
			student = (Student) newSession2.load(Student.class, 1L);
			System.out.println("【after loading Student,but before Hibernate.initialize(student.getContacts())】");
			// 如果使用延迟加载，又想在session关闭之后继续使用被延迟加载的数据，可以使用下面的方式手工强制读取数据
			Hibernate.initialize(student.getContacts());
			newSession2.close();
			System.out.println(student);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (newSession != null) {
				newSession.close();
			}
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		Student student = new Student("Tom Green");
		student.getContacts().add(new Contact("Beijing China", "010-287356772"));
		student.getContacts().add(new Contact("Washington D.C USA", "123-456-789"));
		session.save(student);
		session.getTransaction().commit();
	}
}
