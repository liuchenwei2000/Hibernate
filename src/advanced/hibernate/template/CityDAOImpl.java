/**
 * 
 */
package hibernate.template;

import org.hibernate.Session;

/**
 * DAO实现
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月21日
 */
public class CityDAOImpl implements CityDAO {
	
	private HibernateTemplate<City> htemplate = new HibernateTemplate<City>();

	public void save(final City city) throws CRUDException {
		if (city != null) {
			// 使用这种方式，代码简洁多了
			htemplate.runInTransaction(new HibernateCallback() {

				@Override
				public void execute(Session session) {
					session.save(city);
				}
			});
		}
	}

	public void update(final City city) throws CRUDException {
		if (city != null) {
			htemplate.runInTransaction(new HibernateCallback() {

				@Override
				public void execute(Session session) {
					session.update(city);
				}
			});
		}
	}

	public void delete(final City city) throws CRUDException {
		if (city != null) {
			htemplate.runInTransaction(new HibernateCallback() {

				@Override
				public void execute(Session session) {
					session.delete(city);
				}
			});
		}
	}
}
