/**
 * 
 */
package hibernate.pagination;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * 查询结果分页示例
 * <p>
 * Hibernate中，通过对不同数据库的统一接口设计，实现了透明化、通用化的分页实现机制。
 * <p>
 * 抽象类 org.hibernate.dialect.Dialect 指定了所有底层数据库的对外统一接口，通过针对不同数据库
 * 提供相应的dialect实现，数据库之间的差异性得以消除，从而为上层机制提供了透明的、数据库无关的存储层基础。
 * 在hibernate.cfg.cml中需要配置 <property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月19日
 */
public class PaginationTest extends AbstractHibernateTestCase {

	/**
	 * 将所有的数据进行分页，每页20条
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doTest() throws Exception {
		int numberPerPage = 20;// 每页数据条数
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			// Criteria 和 Query对象都可以完成分页的功能，使用方式是一样的
			
			// 通过 setMaxResults 和  setFirstResult 配合使用达到分页获取数据的目的
			
			Criteria criteria = newSession.createCriteria(Record.class);
//			Query query = newSession.createQuery(" from Record");
			// 设置查询结果最大数目，这里设为每次获取20条，即一页
			criteria.setMaxResults(numberPerPage);

			int counter = 0;
			while (true) {
				// 设置从结果集的第 firstResult 条开始取（基于0开始）
				criteria.setFirstResult((counter++) * numberPerPage);
				// MySQL下的SQL如：select * from tb_records limit 21, 20
				List<Record> result = criteria.list();
				if (!result.isEmpty()) {
					System.out.println("第 " + counter + "批：");
					for (Record record : result) {
						System.out.println(record);
					}
				}
				if (result.size() < numberPerPage) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}
	
	@Override
	protected void prepareData() {
		session.beginTransaction();
		for (int i = 0; i < 100; i++) {
			session.save(new Record("record" + i));
		}
		session.getTransaction().commit();
	}
}
