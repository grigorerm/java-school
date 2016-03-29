package com.db.javaschool.finalproject.dao.newsentry;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.db.javaschool.finalproject.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import com.db.javaschool.finalproject.dao.JpaDao;
import com.db.javaschool.finalproject.entity.Product;

public class JpaProductDao extends JpaDao<Product, Long> implements ProductDao {

	public JpaProductDao() {
		super(Product.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

		Root<Product> root = criteriaQuery.from(Product.class);
		criteriaQuery.orderBy(builder.desc(root.get("id_product")));

		TypedQuery<Product> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
