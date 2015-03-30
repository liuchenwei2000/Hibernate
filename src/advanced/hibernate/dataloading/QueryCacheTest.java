/**
 * 
 */
package hibernate.dataloading;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Query Cache示例
 * <p>
 * Query Cache中保存了之前查询操作执行过的select SQL，以及由此查询产生的查询结果集（包括查询对象的类型和id）。
 * 之后发生查询请求的时候，Hibernate会首先根据查询的SQL从Query Cache中检索，如果此SQL曾经执行过，
 * 则取出对应这个SQL的结果集，再根据这个结果集中的对象类型及其id，从缓存中取出对应的实体对象。
 * <p>
 * 需要在 hibernate.cfg.xml 文件中配置 <property name="hibernate.cache.use_query_cache">true</property>
 * <p>
 * Query Cache 只在特定的情况下产生作用：
 * 1，完全相同的select SQL重复执行。
 * 2，在两次查询之间，此select SQL对应的库表没有发生过改变。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class QueryCacheTest extends AbstractDataLoadingTestCase {

	@Override
	protected void doTest() throws Exception {
		String hql = "from Car where id <4";
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			queryAsList(hql, newSession);
			
			/*
			 * 以下是 list 方法的输出：
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=BMW]
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// 这次查询已经没有SQL输出，说明结果集是完全从缓存取回来的
			queryAsList(hql, newSession);
			
			/*
			 * Query Cache中缓存的SQL及其结果集并非永远存在。
			 * 当Hibernate发现此SQL对应的库表发生了变动（CUD操作），会自动将Query Cache中对应表的SQL缓存废除。
			 */
			/* 删除一条记录 */
			newSession.beginTransaction();
			newSession.createSQLQuery("delete from tb_cars where pk_car=1").executeUpdate();
			newSession.getTransaction().commit();
			
			/*
			 * 以下是 list 方法的输出：
			 * Hibernate: select car0_.pk_car as pk_car1_0_, car0_.name as name2_0_ from tb_cars car0_ where car0_.pk_car<4
			 * 【newSession.createQuery(hql).list()】
			 * Car [name=Benz]
			 * Car [name=Porsche]
			 */
			// 这次查询又有SQL输出，说明Query Cache已经把之前的结果集废除，重新执行了SQL查询。
			queryAsList(hql, newSession);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(newSession != null) {
				newSession.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void queryAsList(String hql, Session newSession) {
		Query query = newSession.createQuery(hql);
		query.setCacheable(true);// 需要将 query的cacheable设为true
		List<Car> cars = query.list();
		System.out.println("【newSession.createQuery(hql).list()】");
		for (Car car : cars) {
			System.out.println(car);
		}
	}
}
