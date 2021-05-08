package cz.vutbr.project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "techer_student_counter")
public class TeacherStudentCounter {

    @Id
    @Column(name = "teacher_id")
    private Long teacher_id;
    @Column(name = "student_count")
    private Long studentCount;
}
