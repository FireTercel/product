package com.clothes.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.clothes.entity.BaseInfo;
import com.clothes.entity.ClothImage;
import com.clothes.entity.Clothes;
import com.clothes.entity.User;
import com.clothes.util.HibernateSessionFactory;
import com.clothes.util.HibernateSessionFactoryUtil;



public class Test {
	HibernateSessionFactory factory=new HibernateSessionFactory();
	
	public static void createTable(){
		
		Configuration cfg=new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
	
	public void getClothes(){
		
		/*String hql="from Clothes";
		Session session=HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		List<Clothes> list=query.list();
		tx.commit();
		Iterator iterator=list.iterator();
		for (Clothes clothes : list) {
			System.out.println(clothes);
		}*/
		
		/*String hql="from BaseInfo";
		Session session=HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		List<BaseInfo> list=query.list();
		tx.commit();
		Iterator iterator=list.iterator();
		for (BaseInfo clothes : list) {
			System.out.println(clothes);
		}*/
		
		/*String hql="from ClothImage";
		Session session=HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		List<ClothImage> list=query.list();
		tx.commit();
		Iterator iterator=list.iterator();
		for (ClothImage clothes : list) {
			System.out.println(clothes);
		}*/
		
		/*String hql="from User";
		Session session=HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		List<User> list=query.list();
		tx.commit();
		Iterator iterator=list.iterator();
		for (User clothes : list) {
			System.out.println(clothes);
		}*/
		
		/*String hql="select ci from ClothImage ci, Clothes c where ci.clothes.id='cp_10000001'" ;
		Session session=HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Query query=session.createQuery(hql);
		List<ClothImage> list=query.list();
		
		
		for (ClothImage clothes : list) {
			System.out.println(clothes);
		}
		tx.commit();*/
		
		Clothes clothes=new Clothes();
		clothes.setCloth_name("clothesName");
		clothes.setCommant("123");
		clothes.setUpload_time(new Date());
		
		Session session=factory.getSession();
		Transaction tx=session.beginTransaction();
		
		session.saveOrUpdate(clothes);

		tx.commit();
		factory.closeSession();
	}
	
	public static void main(String[] args){
		Test test=new Test();
		test.createTable();
	}
	

}
