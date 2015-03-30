/**
 * 
 */
package hibernate.query.HQL.join;

import hibernate.util.AbstractHibernateTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接查询基类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public abstract class AbstractJoinQueryTestCase extends AbstractHibernateTestCase {

	protected void prepareData() {
		session.beginTransaction();
		List<Object> data = createData();
		for (Object row : data) {
			session.save(row);
		}
		session.getTransaction().commit();
	}
	
	private static List<Object> createData() {
		List<Object> data = new ArrayList<Object>();
		
		Employee e1 = new Employee("Tom");
		e1.addDebitCard(new DebitCard("CCB-0928871"));
		e1.addDebitCard(new DebitCard("ABC-109724355"));
		data.add(e1);
		
		Employee e2 = new Employee("Ann");
		e2.addDebitCard(new DebitCard("ICBC-64533881"));
		data.add(e2);
		
		Employee e3 = new Employee("Jimmy");
		data.add(e3);
		
		Employee e4 = new Employee("Peter");
		data.add(e4);
		
		DebitCard dcard = new DebitCard("BOC-8219873651");
		dcard.setEmployee("100");
		data.add(dcard);
		return data;
	}
}
