package cz.vutbr.project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "teachers_students")
public class TeachersStudents {

    @Id
    @Column
    private Long id;
    @Column(name = "teacher_id")
    private Long teacher_id;
    @Column(name = "user_id")
    private Long user_id;
}
