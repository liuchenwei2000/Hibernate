/**
 * 
 */
package hibernate.transaction;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * JDBC Transactionʾ��
 * <p>
 * Hibernate��JDBC����������װ���������߱����������������������ί�и��ײ��JDBC��JTA��ʵ������Ĺ���͵��ȡ�
 * Hibernate��Ĭ����������ƻ��� JDBC Transaction��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��7��
 */
public class JDBCTransactionTest extends AbstractHibernateTestCase  {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		Session newSession = sessionFactory.openSession();
		Transaction tx = newSession.beginTransaction();// �൱�� connection.setAutoCommit(fasle)
		// ...
		tx.commit();// �൱�� connection.commit
		
		// ��SessionFactory���Session��ʼ�����Զ��ύ���Ծ��Ѿ����رգ�autoCommit=false��
		newSession.beginTransaction();
		newSession.save(new DVD("JDBCTransactionTest"));
		newSession.close();// ����û��commit���ر� session�ᵼ�±�������޷����õ����ݿ�
		
		newSession = sessionFactory.openSession();
		List<DVD> result = session.createQuery(" from DVD where name='JDBCTransactionTest' ").list();
		System.out.println("result.size()=" + result.size());// result.size()=0
		
		newSession = sessionFactory.openSession();
		newSession.beginTransaction();
		newSession.save(new DVD("JDBCTransactionTest"));
		// Ҫʹ�����������õ����ݿ⣬��Ҫ��ʽ�ص���Transactionָ�
		newSession.getTransaction().commit();
		newSession.close();
		
		newSession = sessionFactory.openSession();
		result = newSession.createQuery(" from DVD where name='JDBCTransactionTest' ").list();
		System.out.println("result.size()=" + result.size());// result.size()=1
	}
	
	@Override
	protected void prepareData() {
	}
}
