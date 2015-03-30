/**
 * 
 */
package hibernate.template;

/**
 * DAO接口
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月21日
 */
public interface CityDAO {

	public void save(City city) throws CRUDException;

	public void update(City city) throws CRUDException;

	public void delete(City city) throws CRUDException;
}
