/**
 * 
 */
package hibernate.template;

/**
 * DAO�ӿ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��21��
 */
public interface CityDAO {

	public void save(City city) throws CRUDException;

	public void update(City city) throws CRUDException;

	public void delete(City city) throws CRUDException;
}
