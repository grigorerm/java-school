package com.db.javaschool.finalproject.dao.newsentry;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.db.javaschool.finalproject.entity.History;
import org.springframework.transaction.annotation.Transactional;

import com.db.javaschool.finalproject.dao.JpaDao;


public class JpaHistoryDao extends JpaDao<History, Long> implements HistoryDao {

    public JpaHistoryDao() {
        super(History.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> findAll() {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<History> criteriaQuery = builder.createQuery(History.class);

        Root<History> root = criteriaQuery.from(History.class);
        criteriaQuery.orderBy(builder.desc(root.get("id")));

        TypedQuery<History> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

}
