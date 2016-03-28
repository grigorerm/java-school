package com.db.javaschool.finalproject.dao.newsentry;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.db.javaschool.finalproject.entity.Command;
import org.springframework.transaction.annotation.Transactional;

import com.db.javaschool.finalproject.dao.JpaDao;

public class JpaNewsEntryDao extends JpaDao<Command, Long> implements NewsEntryDao {

	public JpaNewsEntryDao() {
		super(Command.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Command> findAll() {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Command> criteriaQuery = builder.createQuery(Command.class);

		Root<Command> root = criteriaQuery.from(Command.class);
		criteriaQuery.orderBy(builder.desc(root.get("date")));

		TypedQuery<Command> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
