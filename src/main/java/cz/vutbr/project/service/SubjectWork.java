package cz.vutbr.project.service;

import cz.vutbr.project.Connection;
import cz.vutbr.project.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubjectWork {

    Connection connection = new Connection();
    Session session = connection.SessionFactory().openSession();

    public String addSubject(Long userId, Long subjectId) {
        Session session = connection.SessionFactory().openSession();
        User user = session.get(User.class, userId);
        String message = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubjectInfo> query = builder.createQuery(StudentSubjectInfo.class);
        Root<StudentSubjectInfo> root = query.from(StudentSubjectInfo.class);



        query.select(root).where(builder.and(builder.equal(root.<String>get("user_id"), userId), builder.equal(root.<String>get("subject_id"), subjectId)));
        List<StudentSubjectInfo> list = session.createQuery(query).getResultList();

            try {
                if (user.getRoles().toString().equals("[TEACHER]")) {
                    return "Cannot add subject for teacher";
                } else {
                   if (list.size() != 1) {
                       Transaction transaction = session.beginTransaction();
                       StudentSubjectInfo subjectInfo = new StudentSubjectInfo();
                       TeachersStudents teachersStudents = new TeachersStudents();
                       subjectInfo.setSubject_id(subjectId);
                       subjectInfo.setUser_id(userId);

                       Subjects subjects = session.get(Subjects.class, subjectId);
                       Long i = subjectId;
                       Long z = subjects.getId();
                       if (i.equals(z)) {
                           teachersStudents.setTeacher_id(subjects.getTeacherId());
                           teachersStudents.setUser_id(userId);
                           TeacherStudentCounter counter = session.get(TeacherStudentCounter.class, subjects.getTeacherId());
                           counter.setStudentCount(counter.getStudentCount() + 1);
                           session.save(teachersStudents);
                           session.save(counter);
                       }

                       session.save(subjectInfo);
                       transaction.commit();
                       return "Successful add subject for Student " + user.getName() + " " + user.getLastname();
                   }else {
                       message = "This subject already added for this student";
                   }
                }
            } catch (NullPointerException e) {
                message = "Something went wrong, chek user ID or Subject ID ";

            }

        return message;

    }

    public String DeleteSubject(String userId, String subjectId) {
        User user = session.get(User.class, Long.valueOf(userId));
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubjectInfo> query = builder.createQuery(StudentSubjectInfo.class);
        Root<StudentSubjectInfo> root = query.from(StudentSubjectInfo.class);
        List<StudentSubjectInfo> subjectsList = session.createQuery(query).getResultList();
        query.select(root);
        for (StudentSubjectInfo subjectInfo : subjectsList) {
            if (subjectInfo.getUser_id().toString().equals(userId) && subjectInfo.getSubject_id().toString().equals(subjectId)) {
                Subjects subjects = session.get(Subjects.class, Long.valueOf(subjectId));
                TeacherStudentCounter counter = session.get(TeacherStudentCounter.class, subjects.getTeacherId());
                counter.setStudentCount(counter.getStudentCount() - 1);
                CriteriaQuery<TeachersStudents> query1 = builder.createQuery(TeachersStudents.class);
                Root<TeachersStudents> root1 = query1.from(TeachersStudents.class);
                query1.select(root1).where(builder.and(builder.equal(root1.get("teacher_id"), subjects.getTeacherId()),
                        builder.equal(root1.get("user_id"),userId)));
                List<TeachersStudents> teachersStudents = session.createQuery(query1).getResultList();

                for (TeachersStudents students : teachersStudents){
                    session.delete(students);
                }
                session.delete(subjectInfo);
                transaction.commit();
                return "successful delete subject for student " + user.getName() + " " + user.getLastname();
            }

        }
        return "Something went wrong, chek user ID or Subject ID ";

    }

    public String AddMark(String userId, String subjectId, String mark) {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubjectInfo> query = builder.createQuery(StudentSubjectInfo.class);
        Root<StudentSubjectInfo> root = query.from(StudentSubjectInfo.class);
        List<StudentSubjectInfo> subjectsList = session.createQuery(query).getResultList();
        query.select(root);
        for (StudentSubjectInfo subjectInfo : subjectsList) {
            if (subjectInfo.getUser_id().toString().equals(userId) && subjectInfo.getSubject_id().toString().equals(subjectId)) {
                subjectInfo.setMark(Float.valueOf(mark));
                session.save(subjectInfo);
                transaction.commit();
                return "Successful add mark for subject";
            }
        }
        return "Something went wrong, chek user ID or Subject ID ";
    }
}
