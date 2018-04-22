package EJB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sujan Maka
 */
@Stateless
public class StudentEJB {

    @PersistenceContext(unitName = "persistence")
    private EntityManager em;

    public List<Student> allStudents() {
        Query q = em.createQuery("SELECT s FROM STUDENT s");
        return (List<Student>) q.getResultList();
    }

    public Student singleStudent(Long id) {
        Query q = em.createQuery("SELECT s FROM STUDENT s where s.id = :id");
        q.setParameter("id", id);
        return (Student) q.getSingleResult();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student saveStudent(Student student) {
        em.persist(student);
        em.flush();
        return student;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student updateStudent(Student student) throws Exception {
        try {
            em.merge(student);
            em.flush();
            return student;
        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteStudent(long id) throws Exception {
        try {
            System.out.println("id : " + id);
            Query q = em.createQuery("SELECT s FROM STUDENT s WHERE s.id = :id");
            q.setParameter("id", id);
            
            em.remove(em.merge(q.getSingleResult()));
            em.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
