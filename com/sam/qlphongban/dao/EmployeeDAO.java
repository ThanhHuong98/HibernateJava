package com.sam.qlphongban.dao;

import com.sam.qlphongban.dto.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeDAO {
    private EntityManager em;
    private EntityManagerFactory emf;

    public EmployeeDAO(){
        emf= Persistence.createEntityManagerFactory("QLNhanVienService");
        em = emf.createEntityManager();
    }

    public List<Employee> readAll(){
        em.clear();
        em.getTransaction().begin();
        List<Employee> result = em.createQuery("from Employee",Employee.class).getResultList();
        em.getTransaction().commit();
        return result;
    }
    public void create(Employee object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }
    public Employee read(int id){
        return em.find(Employee.class, id);
    }

    public void update(Employee object){
        Employee curr = em.find(Employee.class,object.getEid());
        em.getTransaction().begin();
        curr.setEname(object.getEname());
        em.getTransaction().commit();
    }
    public void delete(int id){
        Employee curr = em.find(Employee.class, id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }
}
