package cz.vutbr.project.service;

import cz.vutbr.project.Connection;
import cz.vutbr.project.entity.TeachersStudents;
import cz.vutbr.project.entity.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class Search {


    Connection connection = new Connection();
    Session session = connection.SessionFactory().openSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> root = query.from(User.class);

    public List<User> studentsOfTheTeacher(Long id) {

        CriteriaQuery<User> userQuery = builder.createQuery(User.class);
        List<User> result = new ArrayList<>();
        CriteriaQuery<TeachersStudents> query = builder.createQuery(TeachersStudents.class);
        Root<TeachersStudents> root = query.from(TeachersStudents.class);
        query.select(root).where(builder.equal(root.<String>get("teacher_id"), id));
        List<TeachersStudents> studentsList = session.createQuery(query).getResultList();

        for (TeachersStudents students : studentsList) {
            if (students.getTeacher_id().toString().equals(id.toString())) {
                Long studentId = students.getUser_id();
                User user = session.get(User.class, studentId);
                result.add(user);
            }
        }
        return result;
    }


    public List<User> teachersOfTheStudents(Long id) {

        CriteriaQuery<User> userQuery = builder.createQuery(User.class);
        Root<User> rootUser = userQuery.from(User.class);
        List<User> result = session.createQuery(userQuery).getResultList();
        CriteriaQuery<TeachersStudents> query = builder.createQuery(TeachersStudents.class);
        Root<TeachersStudents> root = query.from(TeachersStudents.class);
        query.select(root).where(builder.equal(root.<String>get("user_id"), id));
        List<TeachersStudents> studentsList = session.createQuery(query).getResultList();
        result.clear();

        for (TeachersStudents teachers : studentsList) {
            if (teachers.getUser_id().toString().equals(id.toString())) {
                Long studentId = teachers.getUser_id();
                User user = session.get(User.class, teachers.getTeacher_id());
                result.add(user);
            }
        }
        return result;
    }


    public List<User> SearchUserById(Long id) {

        try {
            System.out.println("Session open");
            System.out.println("Search User By Id");
            query.select(root).where(builder.equal(root.<String>get("id"), id));
            return session.createQuery(query).getResultList();
        } finally {
            System.out.println("Successful!\nsession Close.");
        }

    }

    public List<User> SearchUserByName(String name) {


        try {
            System.out.println("Session open");
            System.out.println("Searching by User Name");
            query.select(root).where(builder.equal(root.<String>get("name"), name));
            return session.createQuery(query).getResultList();
        } finally {
            System.out.println("Successful!\nsession Close.");
        }

    }

    public List<User> SearchUserByLastname(String lastname) {


        try {
            System.out.println("Session open");
            System.out.println("Search User By Lastname");
            query.select(root).where(builder.equal(root.<String>get("lastname"), lastname));
            return session.createQuery(query).getResultList();
        } finally {
            System.out.println("Successful!\nsession Close.");
        }
    }

    public List<User> SearchUserByYear(String Year) {


        try {
            System.out.println("Session open");
            System.out.println("Search User By Year");
            query.select(root);
            List<User> userList = session.createQuery(query).getResultList();
            List<User> result = session.createQuery(query).getResultList();

            for (User user : userList) {


                String[] list = user.getDOB().split("-");
                if (!list[2].equals(Year)) {
                    result.remove(user);
                }

            }
            return result;
        } finally {
            System.out.println("Successful!\nsession Close.");
        }

    }

}
