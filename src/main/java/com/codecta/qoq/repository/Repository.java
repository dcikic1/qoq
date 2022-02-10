package com.codecta.qoq.repository;

import com.codecta.qoq.repository.entity.ModelObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Repository<T extends ModelObject, PK extends Serializable> {

    private Class<T> entityClass;

    protected Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Repository() {
    }

    @Inject
    EntityManager entityManager;


    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(this.entityClass);
        CriteriaQuery<T> all = cq.select(root);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public T findById(PK id) {

        T result = entityManager.find(this.entityClass, id);
        if (result != null) {
            return result;
        }
        return null;
    }

    public T save(T modelObject) {
        T result = null;
        PK id = (PK) modelObject.getId();
        if (id != null) {
            result = findById(id);
        }
        if (id == null || result != null) {
            modelObject.setCreatedOn(LocalDateTime.now());
            entityManager.persist(modelObject);
            return modelObject;
        }
        return null;
    }

    public void update(T modelObject){
        PK id = (PK) modelObject.getId();
        if(modelObject != null && id != null) {
            modelObject.setModifiedOn(LocalDateTime.now());
            entityManager.merge(modelObject);
        }
    }


}
