/**
 * 
 */
package hibernate.dataloading.lazy;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * 属性的延迟加载示例
 * <p>
 * 如果对实体的某个属性（比如CLOB/BLOB这类比较大的属性）应用延迟加载策略，可以采用两种方法：
 * <li>1，在hbm文件中，将相关的property的lazy属性设为true，然后使用字节码工程将实体类进行编译，植入需要的字节码（主要是处理属性延迟加载的）。
 * <li>2，上面的方式比较费劲，Hibernate推荐使用Critertia的Projection来实现属性的延迟加载。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月17日
 */
public class PropertyLazyLoadingTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		Session newSession = null;
		try {
			newSession = sessionFactory.openSession();
			/*
			 * 通过指定查询的属性从而间接达到延迟加载属性的效果
			 */
			List<?> result = newSession
					.createCriteria(Computer.class)
					.setProjection(
							// 这里设置需要取那些属性和这些属性的别名
							Projections.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("name"), "name"))
					.add(Restrictions.eq("id", 1L))// 这里是查询条件
					.setResultTransformer(// 这里设置将别名列值转换成Bean的转换器
							Transformers.aliasToBean(Computer.class)).list();
			System.out.println(result.get(0));
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
		Computer computer = new Computer("Lenovo");
		computer.setBigdata("this is a big data");
		session.save(computer);
		session.getTransaction().commit();
	}
}
