/**
 * 
 */
package hibernate.orm.discriminator;

import hibernate.util.AbstractHibernateTestCase;

import java.util.List;

/**
 * 场景描述：
 * <p>
 * 共有两种商品，书籍和DVD，这两种商品有一些公共属性，也有各自的特有属性。
 * <p>
 * 对这个场景的库表设计有以下几种：
 * 1，每种商品对应一个单独的表
 * 缺点：一旦公共属性有变化（增加或者修改），则每张表都需要修改。
 * 2，每种商品对应一个只含有特有属性的子表，公共属性放到同一个主表，主子表通过外键关联
 * 缺点：每次查询都需要两张表做关联，性能比较低下。
 * 3，所有商品对应一张表，表中既包含公共属性也包含特有属性，通过某一个类别字段区分不同的商品。
 * 本例就是采用了这样的方案，而对应的对象建模可以采用继承体系。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月24日
 */
public class DiscriminatorTest extends AbstractHibernateTestCase {

	@SuppressWarnings("unchecked")
	@Override
	protected void doTest() {
		session.beginTransaction();
		session.save(createBook());
		session.save(createDVD());
		session.getTransaction().commit();

		// 运行期Hibernate在读取Item对应表的数据时，会根据制定的辨别标识进行判断，自动映射到对应的类。
		List<Book> books = session.createQuery("from Book").list();
		for (Book book : books) {
			System.out.println("Book name is 【" + book.getName()
					+ "】, page count is " + book.getPageCount());
		}
		
		List<DVD> dvds = session.createQuery("from DVD").list();
		for (DVD dvd : dvds) {
			System.out.println("DVD name is 【" + dvd.getName()
					+ "】, region code is " + dvd.getRegionCode());
		}
	}
	
	private static Book createBook(){
		Book book = new Book("Hibernate in Action");
		book.setPageCount(878);
		return book;
	}
	
	private static DVD createDVD(){
		DVD dvd = new DVD("William Tell");
		dvd.setRegionCode("100-090-22983");
		return dvd;
	}

	@Override
	protected void prepareData() {
	}
}
