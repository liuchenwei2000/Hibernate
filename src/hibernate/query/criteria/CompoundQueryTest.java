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
 * ���ϲ�ѯʾ��
 * <p>
 * �����Ǵ���ʾ�����������С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��29��
 */
public class CompoundQueryTest extends AbstractQueryTestCase {

	@SuppressWarnings({ "unchecked", "null", "unused" })
	protected void doTest() {
		Session session = null;
		// �����ѯ�������ÿ����� ICBC ��ͷ����
		Criteria criteria = session.createCriteria(Person2.class);
		// ������� ��������(creditCard��Person2�ķ�������) ����һ�����ϲ�ѯ����
		Criteria addCriteria = criteria.createCriteria("creditCard");
		addCriteria.add(Restrictions.like("no", "ICBC%"));
		// ִ�в�ѯ��SQL�磺
		// select * from tb_person2 person 
		// inner join tb_creditcard2 creditcard on person.pk_person = creditcard.pk_person
		// where creditcard.no like 'ICBC%'
		List<Person2> result = criteria.list();
	}
}
