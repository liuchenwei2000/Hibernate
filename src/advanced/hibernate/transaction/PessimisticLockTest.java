/**
 * 
 */
package hibernate.transaction;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.AbstractHibernateTestCase;

/**
 * ������ʾ��
 * <p>
 * ��������ָ�������ݱ���磨������ǰϵͳ�Լ�����ϵͳ���޸�ʱ�ֱ���̬�ȣ�������������ݴ�������У������ݴ�������״̬��
 * ��������ʵ�֣������������ݿ��ṩ�������ƣ�Ҳֻ�����ݿ���ṩ�������Ʋ���������֤���ݷ��ʵ������ԣ�
 * ����ʹ�ڱ�ϵͳ��ʵ���˼������ƣ�Ҳ�޷���֤�ⲿϵͳ�����޸����ݣ���
 * <p>
 * һ�����͵��������ݿ�ʵ�ֵı��������ã�
 * select * from tb_person where name='tom' for update
 * 
 * ͨ�� for update �Ӿ䣬����SQL������ tb_person �������з��ϼ���������name='tom'���ļ�¼��
 * �ڱ��������ύ֮ǰ�������ύʱ���ͷ���������е�����������޷��޸���Щ��¼��
 * <p>
 * Hibernate�ı�������Ҳ�ǻ������ݿ��������ʵ�֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��7��
 */
public class PessimisticLockTest extends AbstractHibernateTestCase  {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		Session querySession = sessionFactory.openSession();
		
		System.out.println("��QueryTask��Transaction begins");
		querySession.beginTransaction();
		Query query = querySession.createQuery(" from DVD dvd where dvd.id=1");
		/*
		 * Hibernateͨ��ʹ�����ݿ��for update�Ӿ�ʵ���˱��������ƣ�����ģʽ�У�
		 * 
		 * LockMode.NONE����������
		 * LockMode.WRITE��Hibernate��Insert��Update��¼��ʱ����Զ���ȡ
		 * LockMode.READ��Hibernate�ڶ�ȡ��¼��ʱ����Զ���ȡ
		 * 
		 * ����3��������һ����Hibernate�ڲ�ʹ�ã���Ϊ�˱�֤Update�����ж��󲻻ᱻ����޸ģ�����save����ʵ�����Զ�ΪĿ��������WRITE����
		 * ��Щ����Hibernate�ڲ������ݵ��������ƣ������ݿ��޹ء�
		 * 
		 * LockMode.PESSIMISTIC_WRITE���������ݿ��for update�Ӿ�ӱ�����
		 * ����һ��ͨ�����·���ʵ�֣�
		 * Query.setLockMode
		 * Criteria.setLockMode
		 * 
		 * ֻ���ڲ�ѯ֮ǰ��Ҳ��������SQL֮ǰ�����м����Ż�����ͨ�����ݿ�������ƽ��м�������
		 * ���������Ѿ�ͨ��������for update�Ӿ��Select SQL���ؽ�������ν���ݿ����Ҳ��ʧЧ�ˡ�
		 */
		// ���ض���������Ӧ�ļ�¼���м�����������ǶԷ��ص�����DVD��¼���м�����
		query.setLockMode("dvd", LockMode.PESSIMISTIC_WRITE);
		System.out.println("��QueryTask��Query starts");
		List<DVD> result = query.list();
		System.out.println(result);
		System.out.println("��QueryTask��Query finished");
		
		new Thread(new UpdateTask()).start();
		Thread.sleep(1000);
		// ֱ�������ύ֮��Ż��ͷ�����Ҳ����˵UpdateTask�е������ύ��Զ���ڱ������ύ֮��
		querySession.getTransaction().commit();
		System.out.println("��QueryTask��Transaction commits");
		Thread.sleep(1000);
		querySession.close();
	}
	
	private class UpdateTask implements Runnable {

		@Override
		public void run() {
			Session updateSession = sessionFactory.openSession();
			updateSession.beginTransaction();
			System.out.println("��UpdateTask��Transaction begins");
			// �����ȴ����̵߳������ύ���ͷ�����������ִ�� pk_dvd=1 ���ݵĸ��²���
			updateSession.createSQLQuery("update tb_dvds set name='New name' where pk_dvd=1").executeUpdate();
			// ���ִ�е����������  pk_dvd=2����������ȫ�������̼߳�����Ӱ�죬��Ϊ�������������� pk_dvd=1
//			updateSession.createSQLQuery("update tb_dvds set name='New name' where pk_dvd=1").executeUpdate();
			updateSession.getTransaction().commit();
			System.out.println("��UpdateTask��Transaction commits");
			updateSession.close();
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new DVD("Sing a Song"));
		session.save(new DVD("Number 2"));
		session.getTransaction().commit();
	}
}
