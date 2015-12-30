/**
 * 
 */
package hibernate.callback.interceptor;

/**
 * 银行账户
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月4日
 */
public class Account {

	private Long id;
	private String no;
	private double amount;

	public Account() {
		super();
	}

	public Account(String no, double amount) {
		this.no = no;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [no=" + no + ", amount=" + amount + "]";
	}
}
