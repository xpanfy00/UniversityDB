package cz.vutbr.project.service;

import cz.vutbr.project.Connection;
import cz.vutbr.project.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Delete {
    Connection connection = new Connection();
    Session session = connection.SessionFactory().openSession();

    public String DeleteUser(Long id) {


        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TeachersStudents> query = builder.createQuery(TeachersStudents.class);
        Root<TeachersStudents> root = query.from(TeachersStudents.class);
        List<TeachersStudents> teachersStudents = session.createQuery(query).getResultList();
        CriteriaQuery<StudentSubjectInfo> subjectQuery = builder.createQuery(StudentSubjectInfo.class);
        Root<StudentSubjectInfo> subjectRoot = subjectQuery.from(StudentSubjectInfo.class);
        List<StudentSubjectInfo> infoList = session.createQuery(subjectQuery).getResultList();

        User user = session.get(User.class, id);
        Salary salary = session.get(Salary.class, id);

        if (user.getRoles().toString().equals("[TEACHER]")) {
            session.delete(salary);
            for (TeachersStudents teacher : teachersStudents) {
                if (teacher.getTeacher_id().toString().equals(id.toString())) {
                    session.delete(teacher);
                }
            }
        }
        session.delete(user);

        for (TeachersStudents students : teachersStudents) {

            if (students.getUser_id().toString().equals(id.toString())) {
                TeacherStudentCounter counter = session.get(TeacherStudentCounter.class, students.getTeacher_id());
                counter.setStudentCount(counter.getStudentCount() - 1);
                session.delete(students);
            }
        }

        for (StudentSubjectInfo subjectInfo : infoList) {
            if (subjectInfo.getUser_id().equals(id)) {
                session.delete(subjectInfo);
            }
        }


        transaction.commit();
        session.close();
        return "Successful delete user and dependency ";
    }


}
