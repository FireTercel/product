package com.clothes.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.clothes.dao.IClothesDAO;
import com.clothes.entity.Clothes;
import com.clothes.util.HibernateSessionFactory;
import com.mysql.jdbc.Connection;

public class ClothesDAOImpl  implements IClothesDAO {
	
	private HibernateSessionFactory factory;
	

	public HibernateSessionFactory getFactory() {
		factory=new HibernateSessionFactory();
		return factory;
	}

	public void setFactory(HibernateSessionFactory factory) {
		this.factory = factory;
	}
	
	/**
	 * 根据主键查询一条记录
	 */
	@Override
	public Clothes queryClothes(String id) {
		
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		Clothes clothes=(Clothes) session.get(Clothes.class, id);
		
		return clothes;
	}

	/**
	 * 增加一条记录
	 */
	@Override
	public boolean create(Clothes clothes) {
		
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		
		session.saveOrUpdate(clothes);

		tx.commit();
		getFactory().closeSession();
		
		return true;
	}

	/**
	 * 修改一条记录
	 */
	@Override
	public int updateByID(Clothes clothes) {
		int result=0;
		
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		
		session.update(clothes);
		tx.commit();
		getFactory().closeSession();
		
		return result;
	}

	/**
	 * 删除一条记录
	 */
	@Override
	public int deleteByID(Clothes clothes) {
		int result=0;
		
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		
		session.delete(clothes);
		tx.commit();
		getFactory().closeSession();

		return result;
	}

	/**
	 * 查询所有数据
	 */
	@Override
	public List<Clothes> findAll() {
		
		String hql="from Clothes";
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		
		List<Clothes> list=query.list();
		
		return list;
	}

	@Override
	public List<Clothes> findAll(String column, String keyWordString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Clothes> findAll(int currentPage, int lineSize, String column,
			String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询表中记录条数
	 */
	@Override
	public int findCount() {
		Session session=getFactory().getSession();
		Transaction tx=session.beginTransaction();
		String hql = "select count(*) from Clothes";
		Query query = session.createQuery(hql);  
		
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public int findCount(String column, String keyWord) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args){
		
		
		ClothesDAOImpl cImpl=new ClothesDAOImpl();
		int l;
		l=cImpl.findCount();
		
		
			System.out.println(l);
		
	}



}
