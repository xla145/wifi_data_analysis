package com.wzxy.dao.support;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class BaseDaoAbstract<T> {


	@Autowired
	private SessionFactory sessionFactory;

	public abstract Class<T> getEntityClass();

	public Session findSession() {
		return sessionFactory.getCurrentSession();
	}


	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		CriteriaBuilder builder = findSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
		Root<T> root = criteria.from(getEntityClass());
		criteria.select(root);
		return findSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String pro, Object val) {
		CriteriaBuilder builder = findSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
		Root<T> root = criteria.from(getEntityClass());
		criteria.select(root);
		criteria.where(builder.equal(root.get(pro),val));
		return findSession().createQuery(criteria).getSingleResult();
//		return (T) findSession().createCriteria(getEntityClass()).add(Restrictions.eq(pro, val)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String pro, Object val) {
		CriteriaBuilder builder = findSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
		Root<T> root = criteria.from(getEntityClass());
		criteria.select(root);
		criteria.where(builder.equal(root.get(pro),val));
		return findSession().createQuery(criteria).getResultList();
	}

	public int findCounnt() {
		String query = "select count(*) from " + getEntityClass().getName();
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public int findCountLike(String pro, String val) {
		String query = "select count(*) from " + getEntityClass().getName() + " where " + pro + " like '%" + val + "%'";
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public int findCountByProperty(String pro, Object searchValue) {
		String query = "select count(*) from " + getEntityClass().getName() + " where " + pro + " = '" + searchValue
				+ "'";
		return Integer.parseInt(findSession().createQuery(query).uniqueResult().toString());
	}

	public void deleteByProperty(String pro, Object value) {
		String query = "delete from " + getEntityClass().getName() + " where " + pro + "=" + value.toString();
		findSession().createQuery(query).executeUpdate();
	}

	public void deleteByPropertyString(String pro, Object val) {
		String query = "delete from " + getEntityClass().getName() + " where " + pro + "='" + val.toString() + "'";
		findSession().createQuery(query).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public T findById(long id) {
		return (T) findSession().get(getEntityClass().getName(), id);
	}

	public void save(Object obj) {
		findSession().save(obj);
	}
	
	public void modify(Object obj){
		findSession().update(obj);
	}
	
	public void delete(Object obj) {
		findSession().delete(obj);
	}
}
