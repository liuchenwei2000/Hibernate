/**
 * 
 */
package hibernate.query.criteria;

import hibernate.orm.association.one2many.bidirectional.Person2;
import hibernate.query.AbstractQueryTestCase;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * 复合查询示例
 * <p>
 * 仅仅是代码示例，不可运行。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月29日
 */
public class CompoundQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings({ "unchecked", "null", "unused" })
	protected void doTest() {
		Session session = null;
		// 比如查询所有信用卡号以 ICBC 开头的人
		Criteria criteria = session.createCriteria(Person2.class);
		// 这里根据 复合属性(creditCard是Person2的符合属性) 创建一个复合查询条件
		Criteria addCriteria = criteria.createCriteria("creditCard");
		addCriteria.add(Restrictions.like("no", "ICBC%"));
		// 执行查询，SQL如：
		// select * from tb_person2 person 
		// inner join tb_creditcard2 creditcard on person.pk_person = creditcard.pk_person
		// where creditcard.no like 'ICBC%'
		List<Person2> result = criteria.list();
	}
}
