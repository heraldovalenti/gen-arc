package com.genarc.dal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.genarc.model.BaseEntity;
import com.genarc.util.exceptions.TechnicalException;
import com.genarc.util.reflection.ReflectionUtil;

public class AbstractDao<E extends BaseEntity> {
	
	protected EntityManager entityManager;

	private final Class<E> entityClass;

	public AbstractDao() {
		entityClass = ReflectionUtil.getGenericTypeClass(getClass());
	}

	protected Class<E> getEntityClass() {
		return entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public E create(E pData) {
		try {
			entityManager.persist(pData);
			entityManager.flush();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

		return pData;
	}

	public void update(E pData) {
		try {
			E managed = entityManager.merge(pData);
			entityManager.persist(managed);
			entityManager.flush();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	public void delete(Long pKey) {
		try {
			entityManager.remove(retrieve(pKey));
			entityManager.flush();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	public E retrieve(Long pKey) {
		return entityManager.find(getEntityClass(), pKey);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		try {
			String className = getEntityClass().getSimpleName();
			Query query = entityManager.createQuery("SELECT e FROM "
					+ className + " e");

			return query.getResultList();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

	}

	@SuppressWarnings("unchecked")
	public List<E> findByFilter(String filter) {
		try {
			String className = getEntityClass().getSimpleName();
			Query query = entityManager.createNamedQuery(
					className + ".findByFilter")
					.setParameter(":filter", filter);

			return query.getResultList();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

	}

}
