package dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import model.Pager;
import model.SystemContext;

public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;

	private Class<?> clazz;

	public Class<?> getClazz(){
		clazz = ((Class<?>)((((ParameterizedType)
				(this.getClass().getGenericSuperclass()))
				.getActualTypeArguments())[0]));
		return clazz;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T add(T t) {
		getSession().save(t);
		return null;
	}

	@Override
	public void delete(int id) {
		getSession().delete(load(id));
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(int id) {
		return (T) getSession().load(getClass(), id);
	}

	/**
	 * 查询得到不分页结果
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	public List<T> list(String hql, Object[] objs, Map<String, Object> alias){
		hql = initSort(hql);
		Query<T> query = getSession().createQuery(hql);
		setParameter(query, objs);
		setAliasParameter(query, alias);
		return query.list();
	}

	private void setAliasParameter(Query<T> query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for(String key: keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					query.setParameterList(key, (Collection)val);
				}else {
					query.setParameter(key, val);
				}
			}
		}

	}

	private void setParameter(Query query, Object[] objs) {
		if (objs != null && objs.length > 0) {
			int index = 0;
			for(Object obj: objs) {
				query.setParameter(index++, obj);
			}
		}
	}

	/**
	 * 查询顺序
	 * @param hql
	 * @return
	 */
	private String initSort(String hql) {
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		if (sort!=null && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (!"desc".equals(order)) {
				hql += " asc ";
			}else {
				hql += " desc ";
			}
		}
		return hql;
	}

	/**
	 * 返回结果分页
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	public Pager<T> find(String hql, Object[] objs, Map<String, Object> alias){
		hql = initSort(hql);
		Query<T> query = getSession().createQuery(hql);
		setParameter(query, objs);
		setAliasParameter(query, alias);
		Pager<T> pager = new Pager<>();
		setPager(query, pager);
		List<T> datasList = query.list();
		pager.setRows(datasList);

		String countHql = getCountHql(hql);
		Query countQuery = getSession().createQuery(countHql);
		setParameter(countQuery, objs);
		setAliasParameter(countQuery, alias);
		long total = (long) countQuery.uniqueResult();
		pager.setTotal(total);
		return pager;
	}

	/**
	 * 得到求总页数的hql
	 * @param hql
	 * @return
	 */
	private String getCountHql(String hql) {
		String hhqlString = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*)" + hhqlString;
		countHql = countHql.replace("fetch", "");
		return countHql;
	}

	/**
	 * 设置pager对象
	 * @param query
	 * @param pager
	 */
	private void setPager(Query<T> query, Pager<T> pager) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if (pageOffset == null || pageOffset < 0) {
			pageOffset = 0;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		pager.setOffset(pageOffset);
		pager.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}

	/**
	 * 查询特殊对象的语句
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object queryByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objs);
		setAliasParameter(query, alias);
		return query.uniqueResult();

	}

	/**
	 * 更新特殊对象
	 * @param hql
	 * @param objs
	 * @param alias
	 */
	public void updataByHql(String hql, Object[] objs, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objs);
		setAliasParameter(query, alias);
		query.executeUpdate();

	}

}
