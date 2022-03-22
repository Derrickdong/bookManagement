package dao;

import java.util.Map;

public interface BaseDao<T> {

	/*增
	* @param t
	*/
	public T add(T t);
	/*删
	* @param id
	* @return
	*/
	public void delete(int id);
	/*改
	*@param t
	*/
	public void update(T t);
	/*查
	 * @param id
	 * @return
	 */
	public T load(int id);
	
}
