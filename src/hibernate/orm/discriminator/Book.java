/**
 * 
 */
package hibernate.orm.discriminator;

/**
 * 书籍类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月25日
 */
public class Book extends Item {

	private int pageCount;// 特有属性

	public Book() {
		super();
	}

	public Book(String name) {
		super(name, 0);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
