/**
 * 
 */
package hibernate.query.SQL;

import java.util.List;

import org.hibernate.Query;

/**
 * SQL查询示例
 * <p>
 * 由于SQL语法本身的庞杂，以及各种数据库原生功能的多样性，HQL并不能涵盖所有查询特性，有时不得不借助原生SQL或存储过程以达到期望的目标。
 * <p>
 * Hibernate提供了对原生SQL以及存储过程的支持，作为对HQL和Criteria的补充。
 * 相对于基于JDBC的SQL操作，Hibernate提供了更为妥善的封装：
 * 即不需要自己处理ResultSet，ResultSet与实体的映射将由Hibernate自动完成。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class SQLQueryTest extends AbstractSQLQueryTestCase {

	@SuppressWarnings("unchecked")
	protected void doTest() {
		// 纯SQL
		String sql = "select * from tb_dog where pk_dog=1";
		// 创建SQL查询，并指定对应的实体类
		Query query = session.createSQLQuery(sql).addEntity(Dog.class);

		List<Dog> result = query.list();
		for (Dog dog : result) {
			System.out.println(dog);
		}
		
		// 多表联查的SQl
		sql = "select child.*, parent.* from tb_dog child , tb_dog parent where child.pk_parent=parent.pk_dog";
		// 创建SQL查询，并指定对应的实体类，每个实体类都对应结果集中的一部分数据
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
