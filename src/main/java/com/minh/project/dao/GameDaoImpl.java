package com.minh.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minh.project.model.Game;

@Repository
public class GameDaoImpl implements GameDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Game findById(Long id) {
		String hql = "FROM Game g WHERE g.id = :id"; 
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setParameter("id", id);
		return (Game) query.uniqueResult();
	}

	@Override
	public Game findByTitle(String title) {
		String hql = "FROM Gmae g WHERE g.title = :title"; // HQL Query
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setParameter("title", title);
		return (Game) query.uniqueResult();
	}

	@Override
	public void saveGame(Game game) {
		sessionFactory.getCurrentSession().save(game);
	}

	@Override
	public void updateGame(Game game) {
		sessionFactory.getCurrentSession().update(game);	
	}

	@Override
	public void deleteGameById(Long id) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Game WHERE ID = :ID");
		query.setLong("ID", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findAllGames() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Game.class);
        return (List<Game>) criteria.list();
	}

	@Override
	public boolean isGameExist(Game game) {
		Query query = sessionFactory.openSession().createQuery("FROM Game g WHERE g.title = :title");
    	query.setString("title", game.getTitle());
    	List<?> pList = query.list();
    	if (!pList.isEmpty()) { return true; }
    	return false;
	}

}
