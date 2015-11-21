/**
 * 
 */
package hibernate.dataloading;

import hibernate.util.AbstractHibernateTestCase;

/**
 * 数据加载测试用例基类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public abstract class AbstractDataLoadingTestCase extends AbstractHibernateTestCase {

	@Override
	protected void prepareData() {
		session.beginTransaction();
		session.save(new Car("BMW"));
		session.save(new Car("Benz"));
		session.save(new Car("Porsche"));
		session.save(new Car("Ferrari"));
		session.getTransaction().commit();
	}
}
