package cz.vutbr.project.entity;

import cz.vutbr.project.Connection;
import lombok.Data;
import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Entity
@Data
public class Stipend {

    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column
    private Float stipend;

    public float CalculateStipend(Long id) {

        Connection connection = new Connection();
        Session session = connection.SessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubjectInfo> query = builder.createQuery(StudentSubjectInfo.class);
        Root<StudentSubjectInfo> root = query.from(StudentSubjectInfo.class);
        List<StudentSubjectInfo> infoList = session.createQuery(query).getResultList();
        Stipend stipend = session.get(Stipend.class, id);
        User user = session.get(User.class, id);
        float middleMark = 0;
        int i = 0;
        float value = 0;
        if (user.getRoles().toString().equals("[STUDENT]")) {
            for (StudentSubjectInfo subjectInfo : infoList) {
                if (subjectInfo.getUser_id().equals(user.getId())) {
                    middleMark += subjectInfo.getMark();
                    i++;
                }
            }
            middleMark = middleMark / i;
            if (middleMark <= 2) {


                session.getTransaction().begin();
                stipend.setUserId(user.getId());
                stipend.setStipend(Float.valueOf(5000));
                session.save(stipend);
                value = stipend.getStipend();
                session.getTransaction().commit();
            } else {
                value = stipend.getStipend();
            }

        }

        return value;
    }

}
