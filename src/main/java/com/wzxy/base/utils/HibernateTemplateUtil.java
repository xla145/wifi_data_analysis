package com.wzxy.base.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateTemplateUtil {
	
	private static HibernateTemplate hibernateTemplate;
	private static Session session;
	private static SessionFactory sessionFactory;
	
	public static Session getCurrentSession(){
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config/spring-context.xml"});
		
		BeanFactory factory=(BeanFactory)context;
		if(factory.getBean("sessionFactory")!=null){
			sessionFactory=(SessionFactory)factory.getBean("sessionFactory");
			session=sessionFactory.getCurrentSession();
			
		}
		return session;
	}
	
	public static Session getSession(){
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config/spring-context.xml"});
		
		BeanFactory factory=(BeanFactory)context;
		if(factory.getBean("sessionFactory")!=null){
			sessionFactory=(SessionFactory)factory.getBean("sessionFactory");
			session=sessionFactory.openSession();
			
		}
		return session;
		
	}
	
	public static SessionFactory getSessionFactory(){
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config/spring-context.xml"});
		
		BeanFactory factory=(BeanFactory)context;
		if(factory.getBean("sessionFactory")!=null){
			sessionFactory=(SessionFactory)factory.getBean("sessionFactory");	
		}
		return sessionFactory;
		
	}
	
	
	public static  BeanFactory getBeanFactory(){
		
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config/spring-context.xml"});
		 
		BeanFactory factory=(BeanFactory)context;
		
		return factory;
	}
	
	
	public static HibernateTemplate getHibernateTemplate(){
		
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config/spring-context.xml"});
		
		BeanFactory factory=(BeanFactory)context;
		if(factory.getBean("hibernateTemplate")!=null){
			hibernateTemplate=(HibernateTemplate)factory.getBean("hibernateTemplate");
		}
		return hibernateTemplate;
	}
	
	public void close(){
		if(session!=null){
			session.close();
		}
		if(sessionFactory!=null){
			sessionFactory.close();
		}
		
		
	}
	
	public static void main(String[] args){
		
		
		/*Query query=HibernateTemplateUtil.getSession().createSQLQuery("select u.* from user_info u");
		
		List<UserInfo> user=new ArrayList<UserInfo>();
		
		user=query.list();
		
		for(UserInfo u:user){
			System.out.println(u.getUserName());
		}*/
		
	}
	
}
