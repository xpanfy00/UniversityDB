package cz.vutbr.project.service;

import cz.vutbr.project.Connection;
import cz.vutbr.project.entity.Salary;
import cz.vutbr.project.entity.Stipend;
import cz.vutbr.project.entity.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;

public class Info {

    Connection connection = new Connection();
    Session session = connection.SessionFactory().openSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> root = query.from(User.class);
    Integer studentCount = 0;
    Integer teacherCount = 0;
    Integer userCount = 0;
    float studentStipend = 0;
    float salary = 0 ;

    public Integer InfoStudents(){
        query.select(root);
        List<User> userList = session.createQuery(query).getResultList();

        for (User user : userList){
            if (user.getRoles().toString().equals("[STUDENT]")){
                studentCount++;
            }

        }
        return studentCount;
    }
    public Integer InfoTeachers(){
        query.select(root);
        List<User> userList = session.createQuery(query).getResultList();

        for (User user : userList){
            if (user.getRoles().toString().equals("[TEACHER]")){
                teacherCount++;
            }

        }
        return teacherCount;
    }

    public Integer Users(){
        query.select(root);
        List<User> userList = session.createQuery(query).getResultList();

        for (User user : userList){

            userCount++;

        }
        return userCount;
    }

    public float stipends(){
        CriteriaQuery<Stipend> query = builder.createQuery(Stipend.class);
        Root<Stipend> root = query.from(Stipend.class);
        query.select(root);
        List<Stipend> stipends = session.createQuery(query).getResultList();
        for (Stipend stipend : stipends) {
            studentStipend = studentStipend +stipend.getStipend();
        }
        return studentStipend;
    }

    public float salarys(){
        CriteriaQuery<Salary> query = builder.createQuery(Salary.class);
        Root<Salary> root = query.from(Salary.class);
        query.select(root);
        List<Salary> salaries = session.createQuery(query).getResultList();
        for (Salary salary1: salaries) {
            salary = salary + salary1.getSalary();
        }
        return salary;
    }





}
