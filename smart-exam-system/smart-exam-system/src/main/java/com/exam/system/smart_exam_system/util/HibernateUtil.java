package com.exam.system.smart_exam_system.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new

                    Configuration().configure("hibernate.cfg.xml");
            return cfg.buildSessionFactory();
        } catch (Exception e) {

            throw new ExceptionInInitializerError("SessionFactory creation failed: " + e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}