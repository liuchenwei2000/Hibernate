/**
 * 
 */
package hibernate.template;

import org.hibernate.Session;

/**
 * DAOʵ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��21��
 */
public class CityDAOImpl implements CityDAO {
	
	private HibernateTemplate<City> htemplate = new HibernateTemplate<City>();

	public void save(final City city) throws CRUDException {
		if (city != null) {
			// ʹ�����ַ�ʽ�����������
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
