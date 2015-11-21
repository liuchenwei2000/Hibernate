/**
 * 
 */
package hibernate.transaction;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * JDBC Transaction示例
 * <p>
 * Hibernate是JDBC的轻量级封装，本身并不具备事务管理能力，它将事务委托给底层的JDBC或JTA以实现事务的管理和调度。
 * Hibernate的默认事务处理机制基于 JDBC Transaction。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月7日
 */
public class JDBCTransactionTest extends AbstractHibernateTestCase  {

	@SuppressWarnings("unchecked")
	protected void doTest() throws Exception {
		Session newSession = sessionFactory.openSession();
		Transaction tx = newSession.beginTransaction();// 相当于 connection.setAutoCommit(fasle)
		// ...
		tx.commit();// 相当于 connection.commit
		
		// 从SessionFactory获得Session开始，其自动提交属性就已经被关闭（autoCommit=false）
		newSession.beginTransaction();
		newSession.save(new DVD("JDBCTransactionTest"));
		newSession.close();// 这里没有commit即关闭 session会导致保存操作无法作用到数据库
		
		newSession = sessionFactory.openSession();
		List<DVD> result = session.createQuery(" from DVD where name='JDBCTransactionTest' ").list();
		System.out.println("result.size()=" + result.size());// result.size()=0
		
		newSession = sessionFactory.openSession();
		newSession.beginTransaction();
		newSession.save(new DVD("JDBCTransactionTest"));
		// 要使代码真正作用到数据库，需要显式地调用Transaction指令：
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
