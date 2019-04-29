package com.sam.qlphongban.dao;

import com.sam.qlphongban.dto.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DepartmentDAO {
    private EntityManager em;
    private EntityManagerFactory emf;
    public DepartmentDAO(){
        emf = Persistence.createEntityManagerFactory("QLPhongBanService");
        em = emf.createEntityManager();
    }

    public List<Department> readAll(){
        em.clear();
        em.getTransaction().begin();
        List<Department> result = em.createQuery("from Department",Department.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void create(Department object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public Department read(int id){
        return em.find(Department.class,id);
    }
    public void update(Department object){
        Department curr = em.find(Department.class, object.getId());
        em.getTransaction().begin();
        curr.setName(object.getName());
        em.getTransaction().commit();
    }
    public void delete(int id){
        Department curr = em.find(Department.class,id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }
}
