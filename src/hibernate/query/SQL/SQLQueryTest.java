/**
 * 
 */
package hibernate.query.SQL;

import java.util.List;

import org.hibernate.Query;

/**
 * SQL��ѯʾ��
 * <p>
 * ����SQL�﷨��������ӣ��Լ��������ݿ�ԭ�����ܵĶ����ԣ�HQL�����ܺ������в�ѯ���ԣ���ʱ���ò�����ԭ��SQL��洢�����Դﵽ������Ŀ�ꡣ
 * <p>
 * Hibernate�ṩ�˶�ԭ��SQL�Լ��洢���̵�֧�֣���Ϊ��HQL��Criteria�Ĳ��䡣
 * ����ڻ���JDBC��SQL������Hibernate�ṩ�˸�Ϊ���Ƶķ�װ��
 * ������Ҫ�Լ�����ResultSet��ResultSet��ʵ���ӳ�佫��Hibernate�Զ���ɡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��4��
 */
public class SQLQueryTest extends AbstractSQLQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// ��SQL
		String sql = "select * from tb_dog where pk_dog=1";
		// ����SQL��ѯ����ָ����Ӧ��ʵ����
		Query query = session.createSQLQuery(sql).addEntity(Dog.class);

		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
		
		// ��������SQl
		sql = "select child.*, parent.* from tb_dog child , tb_dog parent where child.pk_parent=parent.pk_dog";
		// ����SQL��ѯ����ָ����Ӧ��ʵ���࣬ÿ��ʵ���඼��Ӧ������е�һ��������
		query = session.createSQLQuery(sql).addEntity("child", Dog.class).addEntity("parent", Dog.class);

		List<Object[]> result2 = query.list();
		for (Object[] objects : result2) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
	}
}
