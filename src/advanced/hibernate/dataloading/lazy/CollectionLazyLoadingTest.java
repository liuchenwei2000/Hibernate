/**
 * 
 */
package hibernate.dataloading.lazy;

import hibernate.util.AbstractHibernateTestCase;

import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * ���ϵ��ӳټ���ʾ��
 * <p>
 * Hibernate�ӳټ��ػ����У����ڼ��ϵ��ӳټ�������������Ϊ�ش�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��17��
 */
public class CollectionLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * ���Ҫ�������ϵ��ӳټ��أ�����Ҫ�ڼ���Student����ʱֻ����䱾������ԣ�������Ҫ��ȡStudent����
			 * ��������Contact��Ϣʱ����ִ��student.getContacts���������������ݿ��м���Contact���ݲ����ء�
			 * 
			 * ������£�
			 * Hibernate: select student0_.pk_student as pk_stude1_1_0_, student0_.name as name2_1_0_ from tb_students student0_ where student0_.pk_student=?
			 * ��after loading Student,but before student.getContacts()��
			 * Hibernate: select contacts0_.pk_student as pk_stude4_1_0_, contacts0_.pk_contact as pk_conta1_0_0_, contacts0_.pk_contact as pk_conta1_0_1_, contacts0_.address as address2_0_1_, contacts0_.phone as phone3_0_1_, contacts0_.pk_student as pk_stude4_0_1_ from tb_contacts contacts0_ where contacts0_.pk_student=? order by contacts0_.address asc
			 * Student [name=Tom Green, contacts=[Contact [address=Beijing China, phone=010-287356772], Contact [address=Washington D.C USA, phone=123-456-789]]]
			 * 
			 * �ɼ���һ�β�ѯֻ�ǰ�Student�������Ϣ���˻�������������Contact��Ϣ�ȵ�����student.getContactsʱ�ŷ��������Ĳ�ѯ��
			 */
			Student student = (Student) newSession.load(Student.class, 1L);
			System.out.println("��after loading Student,but before student.getContacts()��");
			System.out.println(student);
			
			Session newSession2 = sessionFactory.openSession();
			student = (Student) newSession2.load(Student.class, 1L);
			System.out.println("��after loading Student,but before Hibernate.initialize(student.getContacts())��");
			// ���ʹ���ӳټ��أ�������session�ر�֮�����ʹ�ñ��ӳټ��ص����ݣ�����ʹ������ķ�ʽ�ֹ�ǿ�ƶ�ȡ����
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
