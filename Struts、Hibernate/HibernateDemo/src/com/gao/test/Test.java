package com.gao.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.gao.wei.News;

public class Test {
	@org.junit.Test
	public void test() {
		//1.创建一个 SessionFactory 对象
		SessionFactory sessionFactory = null;
				
		//1).创建Configuration对象:对应hibernate的基本配置信息和对象关系映射信息
		Configuration configuration = new Configuration().configure();
				
		// Hibernate4.0之前这样创建
        //sessionFactory = configuration.buildSessionFactory();
				
		//2).创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		//hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
		ServiceRegistry serviceRegistry = 
						new ServiceRegistryBuilder().applySettings(configuration.getProperties())
						                            .buildServiceRegistry();
				
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
		//2. 创建一个 Session 对象
		Session session = sessionFactory.openSession();
				
		//3. 开启事务
		Transaction transaction = session.beginTransaction();
				
		//4.执行保存和加载操作
		News news = new News("C++", "zhangsan", new Date(new java.util.Date().getTime()));
		session.save(news);
		
		//加载数据库中id为1的News记录
		News news1 = (News) session.get(News.class,1);
		System.out.println(news);
				
		//5. 提交事务 
		transaction.commit();
				
		//6. 关闭 Session
		session.close();
				
		//7. 关闭 SessionFactory 对象
		sessionFactory.close();
	}
}