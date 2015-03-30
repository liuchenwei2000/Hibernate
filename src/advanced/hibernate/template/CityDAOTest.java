/**
 * 
 */
package hibernate.template;

import hibernate.util.AbstractHibernateTestCase;

/**
 * HibernateTemplate使用示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月21日
 */
public class CityDAOTest extends AbstractHibernateTestCase {

	@Override
	protected void doTest() throws Exception {
		City city1 = new City("Beijing");
		City city2 = new City("Shanghai");
		
		CityDAO dao = new CityDAOImpl();
		
		try {
			dao.save(city1);
			dao.save(city2);
		} catch (Exception e) {
			System.out.println("save failed...");
			e.printStackTrace();
		}
		
		try {
			city1.setName("Guangzhou");
			dao.update(city1);
		} catch (Exception e) {
			System.out.println("update failed...");
			e.printStackTrace();
		}
		
		try {
			dao.delete(city2);
		} catch (Exception e) {
			System.out.println("delete failed...");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void prepareData() {
	}
}