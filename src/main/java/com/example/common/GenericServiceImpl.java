package com.example.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

    public Class<T> clazz;

    public GenericServiceImpl(Class<T> entityClass) {
        this.clazz = entityClass;
    }

    @Override
    public T save(T entity) {
        getEm().persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        getEm().merge(entity);
        return entity;
    }

    @Override
    public void delete(ID id) {
        var em = getEm();
        em.remove(em.find(clazz, id));
    }

    @Override
    public T get(ID id) {
        var em = getEm();
        return em.find(clazz, id);
    }

    @Override
    public List<T> getAll() {
        var em = getEm();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public abstract EntityManager getEm();
}
